package com.customer_reg.kaspon_app.network.model

data class StatesResponse(
    var response: ResponseData
) {
    data class ResponseData(
        val `data`: List<State>,
        val response_code: String
    )

    data class State(var state_id: Int, var state_name: String){
        override fun toString(): String {
            return state_name
        }
    }
}