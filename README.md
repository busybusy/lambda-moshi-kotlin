# lambda-moshi-kotlin
Executes lambda functions using Moshi to serialize/deserialize the data classes

## Basic Usage

```kotlin
implementation("com.github.busybusy.lambda-moshi-kotlin", "core", latestVersion)
```

Implement a class that extends the provided `LambdaStreamHandler` to define what the JSON event and JSON response types should be.

```kotlin
package com.example

class MyApplication : LambdaStreamHandler<MyEvent, MyResult>() {
    override fun handle(event: MyEvent, context: Context?): MyResult {
        return MyResult(event.foo == "test-foo" && event.bar == 100)
    }
}

// Unlike using AWS Lambda's object deserialization with Jackson, you can have non-null and required parameters!!
data class MyEvent(val foo: String, val bar: Int)
data class MyResult(val success: Boolean)
```

When deploying your application, you now instruction lambda to use your class as the handler: `com.example.MyApplication`.

## API Gateway Support

API Gateway handlers are available in the `api-gateway-proxy` module.

```kotlin
implementation("com.github.busybusy.lambda-moshi-kotlin", "api-gateway-proxy", latestVersion)
```

### Using a plain request proxy

```kotlin
class MyApplication : ApiGatewayProxyHandler() {
    override fun handle(request: APIGatewayProxyRequestEvent, context: Context?): APIGatewayProxyResponseEvent {
        if (request.headers["Authorization"] != "Bearer my-custom-token") {
            return createResponse(statusCode = 401)
        }

        // do something with the request.body
        return createResponse(statusCode = 200)
    }
}
```


### Using a parsed JSON body

Sometimes a raw body is not needed when creating API Gateway endpoints. The `ApiGatewayParsedBodyHandler` can be used to
automatically parse the body as JSON as well.

```kotlin
class MySumApplication : ApiGatewayParsedBodyHandler<SumParameters>() {
    override fun handle(request: APIGatewayProxyRequestEvent, body: SumParameters, context: Context?): APIGatewayProxyResponseEvent {
        return createResponse(statusCode = 200, body = "${body.first} + ${body.second} = ${body.first + body.second}")
    }

    data class SumParameters(val first: Int, val second: Int)
}
```