package io.billie.functional.utils

import io.billie.functional.utils.Utils.anOrganizationRequest
import io.billie.organisations.data.OrganisationRepository
import io.billie.organisations.viewmodel.OrganisationRequest
import org.springframework.jdbc.core.JdbcTemplate
import java.util.*

object DbUtils {

    fun createAnOrganization(
        db: OrganisationRepository,
        request: OrganisationRequest = anOrganizationRequest()
    ): MutableMap<String, Any> {
        val id: UUID = db.create(request)

        return orgFromDatabase(db.jdbcTemplate, id)
    }

    fun queryEntityFromDatabase(template: JdbcTemplate, sql: String, id: UUID): MutableMap<String, Any> =
        template.queryForMap(sql, id)

    fun orgFromDatabase(template: JdbcTemplate, id: UUID): MutableMap<String, Any> =
        queryEntityFromDatabase(template, "select * from organisations_schema.organisations where id = ?", id)

    fun contactDetailsFromDatabase(template: JdbcTemplate, id: UUID): MutableMap<String, Any> =
        queryEntityFromDatabase(template, "select * from organisations_schema.contact_details where id = ?", id)

    fun addressFromDatabase(template: JdbcTemplate, id: UUID): MutableMap<String, Any> =
        queryEntityFromDatabase(template, "select * from organisations_schema.addresses where id = ?", id)

}
