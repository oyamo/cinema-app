package com.oyamo.sinema.data.remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.auth.*
import io.ktor.client.features.auth.providers.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object NetClient {
    const val TAG = "NetworkService"

    const val API_KEY = "k_h7yr1vas"
    const val BASE_URL = "https://imdb-api.com/en/API"

    public val CLIENT = HttpClient(CIO) {

        // Error validation
        expectSuccess = false

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }

        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = false
                isLenient = true
            })
        }

        // Response validation
        HttpResponseValidator {
            /** validateResponse { response ->
                val error = response.receive<Error>()
                throw NetException(-1, error.localizedMessage?: "UNKNOWN")
            }
            **/

            handleResponseException { exception ->

                val clientException = exception as? ClientRequestException
                val serverException = exception as? ServerResponseException
                val responseException = exception as? ResponseException

                val generalException: ResponseException =
                    clientException ?: serverException ?: responseException?: return@handleResponseException

                val exceptionResponse =  generalException.response
                val exceptionResponseText = exceptionResponse.readText()

                throw NetException(exceptionResponse.status.value, exceptionResponseText)
            }
        }

        // Timeout
        install(HttpTimeout) {
            requestTimeoutMillis = 15000L
            connectTimeoutMillis = 15000L
            socketTimeoutMillis = 15000L
        }

        defaultRequest {
            // Parameter("api_key", "some_api_key")
            // Content Type
            if (method != HttpMethod.Get) contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)

        }
    }
}