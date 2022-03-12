package com.oyamo.sinema.data.remote

data class NetResponse<T> (
    var statusCode: Int,
    var netException: NetException? = null,
    var data: T? = null
)