package io.busybusy.lambda.moshi.apigateway

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream

internal class ApiGatewayParsedBodyHandlerTest {
    @Test
    fun `it parses the api gateway request and invokes with an instance of the body`() {
        val handler = object : ApiGatewayParsedBodyHandler<BasicBody>() {
            override fun handle(event: APIGatewayProxyRequestEvent, body: BasicBody, context: Context?) = createResponse(204).also {
                assertEquals(200, body.foo)
            }
        }

        val input = """
            {
                "body": "{\"foo\":200}"
            }
        """.trimIndent().toByteArray().inputStream()
        val output = ByteArrayOutputStream()

        handler.handleRequest(input, output, null)
        assertEquals("""{"statusCode":204}""", output.toString())
    }

    data class BasicBody(val foo: Int)
}