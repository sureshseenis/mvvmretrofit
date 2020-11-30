package com.customer_reg.kaspon_app.network.model

data class CountriesResponse(
    var response: ResponseData
) {
    data class ResponseData(
        val `data`: List<Country>,
        val response_code: String
    ) {
        data class Country(var country_id: Int, var country_name: String = "") {
            override fun toString(): String {
                return country_name
            }
        }

    }


}