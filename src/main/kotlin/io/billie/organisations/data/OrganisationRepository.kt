package io.billie.organisations.data

import io.billie.countries.model.CountryResponse
import io.billie.organisations.viewmodel.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.ResultSetExtractor
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.sql.Date
import java.sql.ResultSet
import java.util.*


@Repository
class OrganisationRepository {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @Transactional(readOnly = true)
    fun findOrganisations(): List<OrganisationResponse> {
        return jdbcTemplate.query(organisationQuery(), organisationMapper())
    }

    @Transactional
    fun create(organisation: OrganisationRequest): UUID {
        validate(organisation)

        val id: UUID = createContactDetails(organisation.contactDetails)
        return createOrganisation(organisation, id)
    }

    private fun validate(organisation: OrganisationRequest) {
        if(!countryCodeExists(organisation.countryCode)) {
            throw UnableToFindCountry(organisation.countryCode)
        }

        val address: AddressRequest = organisation.contactDetails.address
        if(!cityExists(address.city, address.countryCode)) {
            throw UnableToFindCity(address.city)
        }
    }

    // You do not have index on this table, but the table is tiny, so the count query should be fine.
    private fun countryCodeExists(countryCode: String): Boolean {
        val reply: Int? = jdbcTemplate.query(
            "select count(country_code) from organisations_schema.countries c WHERE c.country_code = ?",
            ResultSetExtractor {
                it.next()
                it.getInt(1)
            },
            countryCode
        )
        return (reply != null) && (reply > 0)
    }

    // You do not have index on this table, but the table is tiny, so the count query should be fine.
    private fun cityExists(cityName: String, countryCode: String): Boolean {
        val reply: Int? = jdbcTemplate.query(
            "select count(name) from organisations_schema.cities c WHERE c.name = ? AND c.country_code = ?",
            ResultSetExtractor {
                it.next()
                it.getInt(1)
            },
            cityName, countryCode
        )
        return (reply != null) && (reply > 0)
    }

    private fun createOrganisation(org: OrganisationRequest, contactDetailsId: UUID): UUID {
        val keyHolder: KeyHolder = GeneratedKeyHolder()
        jdbcTemplate.update(
            { connection ->
                val ps = connection.prepareStatement(
                    "INSERT INTO organisations_schema.organisations (" +
                            "name, " +
                            "date_founded, " +
                            "country_code, " +
                            "vat_number, " +
                            "registration_number, " +
                            "legal_entity_type, " +
                            "contact_details_id" +
                            ") VALUES (?, ?, ?, ?, ?, ?, ?)",
                    arrayOf("id")
                )
                ps.setString(1, org.name)
                ps.setDate(2, Date.valueOf(org.dateFounded))
                ps.setString(3, org.countryCode)
                ps.setString(4, org.VATNumber)
                ps.setString(5, org.registrationNumber)
                ps.setString(6, org.legalEntityType.toString())
                ps.setObject(7, contactDetailsId)
                ps
            }, keyHolder
        )
        return keyHolder.getKeyAs(UUID::class.java)!!
    }

    private fun createContactDetails(contactDetails: ContactDetailsRequest): UUID {
        val addressId: UUID = createAddress(contactDetails.address)

        val keyHolder: KeyHolder = GeneratedKeyHolder()
        jdbcTemplate.update(
            { connection ->
                val ps = connection.prepareStatement(
                    "insert into organisations_schema.contact_details " +
                            "(" +
                            "phone_number, " +
                            "fax, " +
                            "email, " +
                            "address_id " +
                            ") values(?,?,?,?)",
                    arrayOf("id")
                )
                ps.setString(1, contactDetails.phoneNumber)
                ps.setString(2, contactDetails.fax)
                ps.setString(3, contactDetails.email)
                ps.setObject(4, addressId)
                ps
            },
            keyHolder
        )
        return keyHolder.getKeyAs(UUID::class.java)!!
    }

    private fun createAddress(address: AddressRequest): UUID {
        val keyHolder: KeyHolder = GeneratedKeyHolder()
        jdbcTemplate.update(
            { connection ->
                val ps = connection.prepareStatement(
                    "insert into organisations_schema.addresses " +
                            "(" +
                            "street, " +
                            "house_number, " +
                            "additional, " +
                            "zip_code, " +
                            "city, " +
                            "country_code " +
                            ") values(?,?,?,?,?,?)",
                    arrayOf("id")
                )
                ps.setString(1, address.street)
                ps.setString(2, address.houseNumber)
                ps.setString(3, address.additional)
                ps.setString(4, address.zipCode)
                ps.setString(5, address.city)
                ps.setString(6, address.countryCode)
                ps
            },
            keyHolder
        )
        return keyHolder.getKeyAs(UUID::class.java)!!
    }

    // Just create a view next time please.
    private fun organisationQuery() = "select " +
            "o.id as id, " +
            "o.name as name, " +
            "o.date_founded as date_founded, " +
            "o.country_code as country_code, " +
            "c.id as country_id, " +
            "c.name as country_name, " +
            "o.VAT_number as VAT_number, " +
            "o.registration_number as registration_number," +
            "o.legal_entity_type as legal_entity_type," +
            "o.contact_details_id as contact_details_id, " +
            "cd.phone_number as phone_number, " +
            "cd.fax as fax, " +
            "cd.email as email, " +
            "cd.address_id as address_id, " +
            "ad.street as address_street, " +
            "ad.house_number as address_house_number, " +
            "ad.additional as address_additional, " +
            "ad.zip_code as address_zip_code, " +
            "ad.city as address_city, " +
            "ad.country_code as address_country_code " +
            "from " +
            "organisations_schema.organisations o " +
            "INNER JOIN organisations_schema.contact_details cd on o.contact_details_id::uuid = cd.id::uuid " +
            "INNER JOIN organisations_schema.countries c on o.country_code = c.country_code " +
            "LEFT JOIN organisations_schema.addresses ad on cd.address_id::uuid = ad.id "

    private fun organisationMapper() = RowMapper<OrganisationResponse> { it: ResultSet, _: Int ->
        OrganisationResponse(
            it.getObject("id", UUID::class.java),
            it.getString("name"),
            Date(it.getDate("date_founded").time).toLocalDate(),
            mapCountry(it),
            it.getString("vat_number"),
            it.getString("registration_number"),
            LegalEntityType.valueOf(it.getString("legal_entity_type")),
            mapContactDetails(it)
        )
    }

    private fun mapContactDetails(it: ResultSet): ContactDetails {
        return ContactDetails(
            UUID.fromString(it.getString("contact_details_id")),
            it.getString("phone_number"),
            it.getString("fax"),
            it.getString("email"),
            mapAddress(it)
        )
    }

    private fun mapAddress(it: ResultSet): Address {
        return Address(
            UUID.fromString(it.getString("address_id")),
            it.getString("address_street"),
            it.getString("address_house_number"),
            it.getString("address_additional"),
            it.getString("address_zip_code"),
            it.getString("address_city"),
            it.getString("address_country_code")
        )
    }

    private fun mapCountry(it: ResultSet): CountryResponse {
        return CountryResponse(
            it.getObject("country_id", UUID::class.java),
            it.getString("country_name"),
            it.getString("country_code")
        )
    }

}
