package io.billie.organisations.viewmodel

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * You probably want to validate the addresses somehow, but it is a very hard problem
 */
class AddressRequest (
    val street : String,
    @JsonProperty("house_number") val houseNumber: String,
    val additional: String?,
    @JsonProperty("zip_code") val zipCode: String,
    val city: String,
    @JsonProperty("country_code") val countryCode: String
)
