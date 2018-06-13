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

    abstract fun handle(event: APIGatewayProxyRequestEvent, body: Body, context: Context?): APIGatewayProxyResponseEvent

    final override fun handle(event: APIGatewayProxyRequestEvent, context: Context?)
        = handle(event, bodyAdapter.fromJson(event.body)!!, context)
}