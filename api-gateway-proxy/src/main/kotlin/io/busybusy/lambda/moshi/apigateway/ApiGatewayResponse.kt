package io.busybusy.lambda.moshi.apigateway

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent

/**
 * Alternative response implementation that
 */
class ApiGatewayResponse(statusCode: Int, body: String?, headers: Map<String, String>?) : APIGatewayProxyResponseEvent() {
    init {
        setBody(body)
        setHeaders(headers)
        setStatusCode(statusCode)
    }
}