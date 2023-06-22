package io.billie.functional

import com.fasterxml.jackson.databind.ObjectMapper
import io.billie.functional.utils.DbUtils.addressFromDatabase
import io.billie.functional.utils.DbUtils.contactDetailsFromDatabase
import io.billie.functional.utils.DbUtils.createAnOrganization
import io.billie.functional.utils.DbUtils.orgFromDatabase
import io.billie.functional.data.Fixtures.bbcAddressFixture
import io.billie.functional.data.Fixtures.bbcContactFixture
import io.billie.functional.data.Fixtures.updateContactDetailsRequest
import io.billie.functional.data.Fixtures.updateContactDetailsRequestInvalidCity
import io.billie.functional.data.Fixtures.updateContactDetailsRequestInvalidCountryCode
import io.billie.functional.data.Fixtures.updateContactDetailsRequestMissingAddress
import io.billie.functional.utils.Utils.assertDataMatches
import io.billie.organisations.data.OrganisationRepository
import io.billie.organisations.viewmodel.Entity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = DEFINED_PORT)
class UpdateOrganisationContactDetailsTest {

    @LocalServerPort
    private val port = 8080

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper: ObjectMapper

    @Autowired
    private lateinit var template: JdbcTemplate

    @Autowired
    private lateinit var db: OrganisationRepository


    @Test
    fun returns404WhenOrgDoesNotExist() {
        mockMvc.perform(
            put("/organisations/${UUID.randomUUID()}/contact-details").contentType(APPLICATION_JSON)
                .content(updateContactDetailsRequest())
        )
            .andExpect(status().isNotFound)
    }

    @Test
    fun cannotUpdateOrgWhenNoAddress() {
        val organisationDetails: Map<String, Any> = createAnOrganization(db)
        val orgId: UUID = organisationDetails["id"] as UUID

        mockMvc.perform(
            put("/organisations/${orgId}/contact-details").contentType(APPLICATION_JSON)
                .content(updateContactDetailsRequestMissingAddress())
        )
            .andExpect(status().isBadRequest)
    }

    @Test
    fun cannotUpdateOrgWhenAddressCityInvalid() {
        val organisationDetails: Map<String, Any> = createAnOrganization(db)
        val orgId: UUID = organisationDetails["id"] as UUID

        mockMvc.perform(
            put("/organisations/${orgId}/contact-details").contentType(APPLICATION_JSON)
                .content(updateContactDetailsRequestInvalidCity())
        )
            .andExpect(status().isBadRequest)
    }

    @Test
    fun cannotUpdateOrgWhenAddressCountryCodeInvalid() {
        val organisationDetails: Map<String, Any> = createAnOrganization(db)
        val orgId: UUID = organisationDetails["id"] as UUID

        mockMvc.perform(
            put("/organisations/${orgId}/contact-details").contentType(APPLICATION_JSON)
                .content(updateContactDetailsRequestInvalidCountryCode())
        )
            .andExpect(status().isBadRequest)
    }

    @Test
    fun canUpdateContactDetails() {
        val organisationDetails: Map<String, Any> = createAnOrganization(db)
        val orgId: UUID = organisationDetails["id"] as UUID

        val result = mockMvc.perform(
            put("/organisations/${orgId}/contact-details").contentType(APPLICATION_JSON)
                .content(updateContactDetailsRequest())
        )
            .andExpect(status().isOk)
            .andReturn()

        val response = mapper.readValue(result.response.contentAsString, Entity::class.java)

        val contactDetailsId: UUID = response.id
        val contactDetails: Map<String, Any> = contactDetailsFromDatabase(template, contactDetailsId)
        assertDataMatches(contactDetails, bbcContactFixture(contactDetailsId))

        val addressId: UUID = UUID.fromString(contactDetails["address_id"] as String)
        val addressDetails: Map<String, Any> = addressFromDatabase(template, addressId)
        assertDataMatches(addressDetails, bbcAddressFixture(addressId))

        val updatedOrganisationDetails: Map<String, Any> = orgFromDatabase(template, orgId)
        val updatedContactId: UUID = UUID.fromString(updatedOrganisationDetails["contact_details_id"] as String)
        assertEquals(contactDetailsId, updatedContactId)
    }
}
