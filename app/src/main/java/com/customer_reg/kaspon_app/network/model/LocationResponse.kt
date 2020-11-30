package com.customer_reg.kaspon_app.network.model

data class LocationResponse(
    var response: ResponseData

) {
    data class ResponseData(
        val `data`: List<LocationData>,
        val response_code: String
    )

    data class LocationData(
        val location_id: Int,
        val location_name: String
    ){
        override fun toString(): String {
            return location_name
        }
    }
}