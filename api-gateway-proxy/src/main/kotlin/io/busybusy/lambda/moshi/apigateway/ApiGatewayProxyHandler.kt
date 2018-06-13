package io.busybusy.lambda.moshi.apigateway

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import io.busybusy.lambda.moshi.LambdaStreamHandler

abstract class ApiGatewayProxyHandler : LambdaStreamHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>() {
    fun createResponse(statusCode: Int, body: String? = null, headers: Map<String, String>? = null)
        = ApiGatewayResponse(statusCode, body, headers)
}