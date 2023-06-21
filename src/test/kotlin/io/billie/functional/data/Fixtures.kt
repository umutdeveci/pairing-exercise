package io.billie.functional.data

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

object Fixtures {

    fun orgRequestJsonNameBlank(): String {
        return "{\n" +
                "  \"name\": \"\",\n" +
                "  \"date_founded\": \"18/10/1922\",\n" +
                "  \"country_code\": \"GB\",\n" +
                "  \"vat_number\": \"333289454\",\n" +
                "  \"registration_number\": \"3686147\",\n" +
                "  \"legal_entity_type\": \"NONPROFIT_ORGANIZATION\",\n" +
                "  \"contact_details\": {\n" +
                "    \"phone_number\": \"+443700100222\",\n" +
                "    \"fax\": \"\",\n" +
                "    \"email\": \"yourquestions@bbc.co.uk\",\n" +
                "    \"address\": {\n" +
                "       \"street\": \"Television Centre\",\n" +
                "       \"house_number\": \"1\",\n" +
                "       \"additional\": \"additional\",\n" +
                "       \"zip_code\": \"W12 7FA\",\n" +
                "       \"city\": \"London\",\n" +
                "       \"country_code\": \"GB\"\n" +
                "     }\n" +
                "  }\n" +
                "}"
    }

    fun orgRequestJsonNoName(): String {
        return "{\n" +
                "  \"date_founded\": \"18/10/1922\",\n" +
                "  \"country_code\": \"GB\",\n" +
                "  \"vat_number\": \"333289454\",\n" +
                "  \"registration_number\": \"3686147\",\n" +
                "  \"legal_entity_type\": \"NONPROFIT_ORGANIZATION\",\n" +
                "  \"contact_details\": {\n" +
                "    \"phone_number\": \"+443700100222\",\n" +
                "    \"fax\": \"\",\n" +
                "    \"email\": \"yourquestions@bbc.co.uk\",\n" +
                "    \"address\": {\n" +
                "       \"street\": \"Television Centre\",\n" +
                "       \"house_number\": \"1\",\n" +
                "       \"additional\": \"additional\",\n" +
                "       \"zip_code\": \"W12 7FA\",\n" +
                "       \"city\": \"London\",\n" +
                "       \"country_code\": \"GB\"\n" +
                "     }\n" +
                "  }\n" +
                "}"
    }

    fun orgRequestJsonNoLegalEntityType(): String {
        return "{\n" +
                "  \"name\": \"BBC\",\n" +
                "  \"date_founded\": \"18/10/1922\",\n" +
                "  \"country_code\": \"GB\",\n" +
                "  \"vat_number\": \"333289454\",\n" +
                "  \"registration_number\": \"3686147\",\n" +
                "  \"contact_details\": {\n" +
                "    \"phone_number\": \"+443700100222\",\n" +
                "    \"fax\": \"\",\n" +
                "    \"email\": \"yourquestions@bbc.co.uk\",\n" +
                "    \"address\": {\n" +
                "       \"street\": \"Television Centre\",\n" +
                "       \"house_number\": \"1\",\n" +
                "       \"additional\": \"additional\",\n" +
                "       \"zip_code\": \"W12 7FA\",\n" +
                "       \"city\": \"London\",\n" +
                "       \"country_code\": \"GB\"\n" +
                "     }\n" +
                "  }\n" +
                "}"
    }

    fun orgRequestJsonNoContactDetails(): String {
        return "{\n" +
                "  \"name\": \"BBC\",\n" +
                "  \"date_founded\": \"18/10/1922\",\n" +
                "  \"country_code\": \"GB\",\n" +
                "  \"vat_number\": \"333289454\",\n" +
                "  \"registration_number\": \"3686147\",\n" +
                "  \"legal_entity_type\": \"NONPROFIT_ORGANIZATION\"\n" +
                "}"
    }

    fun orgRequestJson(): String {
        return "{\n" +
                "  \"name\": \"BBC\",\n" +
                "  \"date_founded\": \"18/10/1922\",\n" +
                "  \"country_code\": \"GB\",\n" +
                "  \"vat_number\": \"333289454\",\n" +
                "  \"registration_number\": \"3686147\",\n" +
                "  \"legal_entity_type\": \"NONPROFIT_ORGANIZATION\",\n" +
                "  \"contact_details\": {\n" +
                "    \"phone_number\": \"+443700100222\",\n" +
                "    \"fax\": \"\",\n" +
                "    \"email\": \"yourquestions@bbc.co.uk\",\n" +
                "    \"address\": {\n" +
                "       \"street\": \"Television Centre\",\n" +
                "       \"house_number\": \"1\",\n" +
                "       \"additional\": \"additional\",\n" +
                "       \"zip_code\": \"W12 7FA\",\n" +
                "       \"city\": \"London\",\n" +
                "       \"country_code\": \"GB\"\n" +
                "     }\n" +
                "  }\n" +
                "}"
    }

    fun orgRequestJsonCountryCodeBlank(): String {
        return "{\n" +
                "  \"name\": \"BBC\",\n" +
                "  \"date_founded\": \"18/10/1922\",\n" +
                "  \"country_code\": \"\",\n" +
                "  \"vat_number\": \"333289454\",\n" +
                "  \"registration_number\": \"3686147\",\n" +
                "  \"legal_entity_type\": \"NONPROFIT_ORGANIZATION\",\n" +
                "  \"contact_details\": {\n" +
                "    \"phone_number\": \"+443700100222\",\n" +
                "    \"fax\": \"\",\n" +
                "    \"email\": \"yourquestions@bbc.co.uk\",\n" +
                "    \"address\": {\n" +
                "       \"street\": \"Television Centre\",\n" +
                "       \"house_number\": \"1\",\n" +
                "       \"additional\": \"additional\",\n" +
                "       \"zip_code\": \"W12 7FA\",\n" +
                "       \"city\": \"London\",\n" +
                "       \"country_code\": \"GB\"\n" +
                "     }\n" +
                "  }\n" +
                "}"
    }

    fun orgRequestJsonNoCountryCode(): String {
        return "{\n" +
                "  \"name\": \"BBC\",\n" +
                "  \"date_founded\": \"18/10/1922\",\n" +
                "  \"vat_number\": \"333289454\",\n" +
                "  \"registration_number\": \"3686147\",\n" +
                "  \"legal_entity_type\": \"NONPROFIT_ORGANIZATION\",\n" +
                "  \"contact_details\": {\n" +
                "    \"phone_number\": \"+443700100222\",\n" +
                "    \"fax\": \"\",\n" +
                "    \"email\": \"yourquestions@bbc.co.uk\",\n" +
                "    \"address\": {\n" +
                "       \"street\": \"Television Centre\",\n" +
                "       \"house_number\": \"1\",\n" +
                "       \"additional\": \"additional\",\n" +
                "       \"zip_code\": \"W12 7FA\",\n" +
                "       \"city\": \"London\",\n" +
                "       \"country_code\": \"GB\"\n" +
                "     }\n" +
                "  }\n" +
                "}"
    }

    fun orgRequestJsonCountryCodeIncorrect(): String {
        return "{\n" +
                "  \"name\": \"BBC\",\n" +
                "  \"date_founded\": \"18/10/1922\",\n" +
                "  \"country_code\": \"XX\",\n" +
                "  \"vat_number\": \"333289454\",\n" +
                "  \"registration_number\": \"3686147\",\n" +
                "  \"legal_entity_type\": \"NONPROFIT_ORGANIZATION\",\n" +
                "  \"contact_details\": {\n" +
                "    \"phone_number\": \"+443700100222\",\n" +
                "    \"fax\": \"\",\n" +
                "    \"email\": \"yourquestions@bbc.co.uk\",\n" +
                "    \"address\": {\n" +
                "       \"street\": \"Television Centre\",\n" +
                "       \"house_number\": \"1\",\n" +
                "       \"additional\": \"additional\",\n" +
                "       \"zip_code\": \"W12 7FA\",\n" +
                "       \"city\": \"London\",\n" +
                "       \"country_code\": \"GB\"\n" +
                "     }\n" +
                "  }\n" +
                "}"
    }

    fun orgRequestJsonAddressMissing(): String {
        return "{\n" +
                "  \"name\": \"BBC\",\n" +
                "  \"date_founded\": \"18/10/1922\",\n" +
                "  \"country_code\": \"GB\",\n" +
                "  \"vat_number\": \"333289454\",\n" +
                "  \"registration_number\": \"3686147\",\n" +
                "  \"legal_entity_type\": \"NONPROFIT_ORGANIZATION\",\n" +
                "  \"contact_details\": {\n" +
                "    \"phone_number\": \"+443700100222\",\n" +
                "    \"fax\": \"\",\n" +
                "    \"email\": \"yourquestions@bbc.co.uk\"\n" +
                "  }\n" +
                "}"
    }

    fun orgRequestJsonAddressCityInvalid(): String {
        return "{\n" +
                "  \"name\": \"BBC\",\n" +
                "  \"date_founded\": \"18/10/1922\",\n" +
                "  \"country_code\": \"GB\",\n" +
                "  \"vat_number\": \"333289454\",\n" +
                "  \"registration_number\": \"3686147\",\n" +
                "  \"legal_entity_type\": \"NONPROFIT_ORGANIZATION\",\n" +
                "  \"contact_details\": {\n" +
                "    \"phone_number\": \"+443700100222\",\n" +
                "    \"fax\": \"\",\n" +
                "    \"email\": \"yourquestions@bbc.co.uk\",\n" +
                "    \"address\": {\n" +
                "       \"street\": \"Television Centre\",\n" +
                "       \"house_number\": \"1\",\n" +
                "       \"additional\": \"additional\",\n" +
                "       \"zip_code\": \"W12 7FA\",\n" +
                "       \"city\": \"does_not_exist\",\n" +
                "       \"country_code\": \"GB\"\n" +
                "     }\n" +
                "  }\n" +
                "}"
    }

    fun orgRequestJsonAddressCountryCodeInvalid(): String {
        return "{\n" +
                "  \"name\": \"BBC\",\n" +
                "  \"date_founded\": \"18/10/1922\",\n" +
                "  \"country_code\": \"GB\",\n" +
                "  \"vat_number\": \"333289454\",\n" +
                "  \"registration_number\": \"3686147\",\n" +
                "  \"legal_entity_type\": \"NONPROFIT_ORGANIZATION\",\n" +
                "  \"contact_details\": {\n" +
                "    \"phone_number\": \"+443700100222\",\n" +
                "    \"fax\": \"\",\n" +
                "    \"email\": \"yourquestions@bbc.co.uk\",\n" +
                "    \"address\": {\n" +
                "       \"street\": \"Television Centre\",\n" +
                "       \"house_number\": \"1\",\n" +
                "       \"additional\": \"additional\",\n" +
                "       \"zip_code\": \"W12 7FA\",\n" +
                "       \"city\": \"London\",\n" +
                "       \"country_code\": \"DE\"\n" +
                "     }\n" +
                "  }\n" +
                "}"
    }

    fun bbcFixture(id: UUID): Map<String, Any> {
        val data = HashMap<String, Any>()
        data["id"] = id
        data["name"] = "BBC"
        data["date_founded"] = SimpleDateFormat("yyyy-MM-dd").parse("1922-10-18")
        data["country_code"] = "GB"
        data["vat_number"] = "333289454"
        data["registration_number"] = "3686147"
        data["legal_entity_type"] = "NONPROFIT_ORGANIZATION"
        return data
    }

    fun bbcContactFixture(id: UUID): Map<String, Any> {
        val data = HashMap<String, Any>()
        data["id"] = id
        data["phone_number"] = "+443700100222"
        data["fax"] = ""
        data["email"] = "yourquestions@bbc.co.uk"
        return data
    }

    fun bbcAddressFixture(id: UUID): Map<String, Any> {
        val data = HashMap<String, Any>()
        data["id"] = id
        data["street"] = "Television Centre"
        data["house_number"] = "1"
        data["additional"] = "additional"
        data["zip_code"] = "W12 7FA"
        data["city"] = "London"
        data["country_code"] = "GB"
        return data
    }


}
