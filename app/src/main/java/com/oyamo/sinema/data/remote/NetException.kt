package com.oyamo.sinema.data.remote

class NetException(
    val statusCode: Int,
    private val description: String,
): Exception(description)