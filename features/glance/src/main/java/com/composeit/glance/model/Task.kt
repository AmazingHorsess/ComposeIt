package com.composeit.glance.model
import kotlinx.serialization.Serializable

@Serializable
internal data class Task(
    val id: Long,
    val title: String,
)
