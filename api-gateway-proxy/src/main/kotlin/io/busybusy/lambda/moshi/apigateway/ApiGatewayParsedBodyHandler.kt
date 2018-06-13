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
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import com.squareup.moshi.JsonAdapter
import io.busybusy.lambda.moshi.supertypeArguments
import java.lang.reflect.Type
import kotlin.reflect.jvm.javaType

abstract class ApiGatewayParsedBodyHandler<Body : Any> : ApiGatewayProxyHandler() {
    private val bodyType: Type = supertypeArguments(ApiGatewayParsedBodyHandler::class)[0].javaType
    private val bodyAdapter: JsonAdapter<Body> by lazy { moshi.adapter<Body>(bodyType) }

    protected abstract fun handle(event: APIGatewayProxyRequestEvent, body: Body, context: Context?): APIGatewayProxyResponseEvent

    final override fun handle(event: APIGatewayProxyRequestEvent, context: Context?)
        = handle(event, bodyAdapter.fromJson(event.body)!!, context)
}