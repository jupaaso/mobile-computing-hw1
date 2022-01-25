package com.codemave.mobilecomputing3.data.entity

import java.util.*

data class Payment(
    val paymentId: Long,
    val paymentTitle: String,
    val paymentDate: Date?,
    val paymentCategory: String
)
