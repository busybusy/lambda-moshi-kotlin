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

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import io.busybusy.lambda.moshi.LambdaStreamHandler

abstract class ApiGatewayProxyHandler : LambdaStreamHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>() {
    fun createResponse(statusCode: Int, body: String? = null, headers: Map<String, String>? = null)
        = ApiGatewayResponse(statusCode, body, headers)
}