package com.customer_reg.kaspon_app.network.model

data class AmcResponse(
    var response:ResponseData

) {
    data class ResponseData(val `data`: List<AmcDetails>,
                            val response_code: String)
    data class AmcDetails(var id: Int = 0, var amc_type: String = ""){
        override fun toString(): String {
            return amc_type
        }
    }
}
