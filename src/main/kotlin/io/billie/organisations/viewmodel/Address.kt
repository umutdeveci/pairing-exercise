package io.billie.organisations.viewmodel

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class Address (
    val id: UUID?,
    val street : String?,
    @JsonProperty("house_number") val houseNumber: String?,
    val additional: String?,
    @JsonProperty("zip_code") val zipCode: String?,
    val city: String?,
    @JsonProperty("country_code") val countryCode: String?
)
