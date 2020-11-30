package com.customer_reg.kaspon_app.network.model

data class CitiesResponse(
    var response: ResponseData

) {
    data class ResponseData(
        val `data`: List<City>,
        val response_code: String
    )

    data class City(var city_id: Int, var city_name: String){
        override fun toString(): String {
            return city_name
        }
    }
}