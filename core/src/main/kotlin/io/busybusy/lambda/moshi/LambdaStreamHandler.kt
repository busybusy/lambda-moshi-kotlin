package io.busybusy.lambda.moshi

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestStreamHandler
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okio.Okio
import java.io.InputStream
import java.io.OutputStream
import java.lang.reflect.Type
import kotlin.reflect.jvm.javaType

abstract class LambdaStreamHandler<Event : Any, Result : Any> : RequestStreamHandler {
    val moshi: Moshi by lazy { createMoshiBuilder().build() }

    private inline val typeArguments get() = supertypeArguments(LambdaStreamHandler::class)
    private val eventType: Type = typeArguments[0].javaType
    private val resultType: Type = typeArguments[1].javaType

    private val eventAdapter: JsonAdapter<Event> by lazy { moshi.adapter<Event>(eventType) }
    private val resultAdapter: JsonAdapter<Result> by lazy { moshi.adapter<Result>(resultType) }

    abstract fun handle(event: Event, context: Context?): Result

    open fun createMoshiBuilder(): Moshi.Builder = Moshi.Builder().apply {
        add(KotlinJsonAdapterFactory())
    }

    final override fun handleRequest(input: InputStream, output: OutputStream, context: Context?) {
        val event = eventAdapter.fromJson(input) ?: error("Failed to parse incoming event to $eventType")
        val result = handle(event, context)

        resultAdapter.toJson(output, result)
    }

    private fun <T> JsonAdapter<T>.fromJson(input: InputStream) = fromJson(Okio.buffer(Okio.source(input)))
    private fun <T> JsonAdapter<T>.toJson(output: OutputStream, value: T?) {
        val outputBuffer = Okio.buffer(Okio.sink(output))
        toJson(outputBuffer, value)
        outputBuffer.flush()
    }
}