package com.customer_reg.kaspon_app.network.model

data class SubProductResponse(
    var response: ResponseData

) {
    data class ResponseData(
        val `data`: List<SubProduct>,
        val response_code: String
    )

    data class SubProduct(var id: Int, var product_sub_name: String) {
        override fun toString(): String {
            return product_sub_name
        }
    }
}