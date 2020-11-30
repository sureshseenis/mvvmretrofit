package com.customer_reg.kaspon_app.network.model

data class ProductResponse(
    var response: ResponseData

) {
    data class ResponseData(
        val `data`: List<ProductDetails>,
        val response_code: String
    )

    data class ProductDetails(
        val product_description: String,
        val product_id: Int,
        val product_model: String,
        val product_name: String
    ) {
        override fun toString(): String {
            return product_name
        }
    }
}
