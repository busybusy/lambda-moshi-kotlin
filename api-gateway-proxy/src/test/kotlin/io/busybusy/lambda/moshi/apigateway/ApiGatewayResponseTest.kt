package io.busybusy.lambda.moshi.apigateway

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ApiGatewayResponseTest {
    @Test
    fun `it properly sets the values`() {
        val response = ApiGatewayResponse(statusCode = 200, body = "It worked!", headers = mapOf("Content-Type" to "text/plain"))

        assertEquals(200, response.statusCode)
        assertEquals("It worked!", response.body)
        assertEquals(mapOf("Content-Type" to "text/plain"), response.headers)
    }
}