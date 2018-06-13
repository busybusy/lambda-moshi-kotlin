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