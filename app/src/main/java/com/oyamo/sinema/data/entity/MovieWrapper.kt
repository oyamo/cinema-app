package com.oyamo.sinema.data.entity

import kotlinx.serialization.Serializable


@Serializable
data class MovieWrapper(
    val errorMessage: String,
    val items: List<MovieItem>
)