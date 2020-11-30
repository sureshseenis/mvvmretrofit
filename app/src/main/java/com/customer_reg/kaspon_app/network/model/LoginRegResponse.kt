package com.customer_reg.kaspon_app.network.model

data class LoginRegResponse(
    val message: String,
    val response_code: String,
    val token: String
)