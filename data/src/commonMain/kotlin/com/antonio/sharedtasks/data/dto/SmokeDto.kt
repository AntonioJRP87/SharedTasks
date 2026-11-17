package com.antonio.sharedtasks.data.dto

import kotlinx.serialization.Serializable
@Serializable
data class SmokeDto (
    val text: String = "",
    val num: Int = 0
)
