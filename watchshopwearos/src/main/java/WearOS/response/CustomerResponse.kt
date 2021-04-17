package WearOS.response

import WearOS.entity.Customer

data class CustomerResponse(
    val data: Customer? = null,
    val success: Boolean? = null
)
