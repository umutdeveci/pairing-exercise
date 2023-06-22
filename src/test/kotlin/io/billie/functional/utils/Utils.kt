package io.billie.functional.utils

import io.billie.organisations.viewmodel.AddressRequest
import io.billie.organisations.viewmodel.ContactDetailsRequest
import io.billie.organisations.viewmodel.LegalEntityType
import io.billie.organisations.viewmodel.OrganisationRequest
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsEqual
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Utils {
    fun assertDataMatches(reply: Map<String, Any>, assertions: Map<String, Any>) {
        for (key in assertions.keys) {
            MatcherAssert.assertThat(reply[key], IsEqual.equalTo(assertions[key]))
        }
    }

    fun anOrganizationRequest(
        name: String = "test_org_name",
        dateFounded: LocalDate = LocalDate.parse("01/01/2023", DateTimeFormatter.ofPattern("dd/MM/yyy")),
        countryCode: String = "GB",
        vatNumber: String = "123456",
        registrationNumber: String = "654321",
        legalEntityType: LegalEntityType = LegalEntityType.SOLE_PROPRIETORSHIP,
        contactDetailsRequest: ContactDetailsRequest = aContactDetailRequest()
    ): OrganisationRequest {
        return OrganisationRequest(
            name,
            dateFounded,
            countryCode,
            vatNumber,
            registrationNumber,
            legalEntityType,
            contactDetailsRequest
        )
    }

    fun aContactDetailRequest(
        phoneNumber: String = "11111111",
        fax: String = "22222222",
        email: String = "test@example.com",
        addressRequest: AddressRequest = anAddressRequest()
    ): ContactDetailsRequest {
        return ContactDetailsRequest(
            phoneNumber,
            fax,
            email,
            addressRequest
        )
    }

    fun anAddressRequest(
        street: String = "test street",
        houseNumber: String = "123",
        additional: String = "additional",
        zipCode: String = "zipcode",
        city: String = "London",
        countryCode: String = "GB"
    ): AddressRequest {
        return AddressRequest(
            street,
            houseNumber,
            additional,
            zipCode,
            city,
            countryCode
        )
    }

}
