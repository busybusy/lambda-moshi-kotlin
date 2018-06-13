/**
 * Copyright 2018 busybusy, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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