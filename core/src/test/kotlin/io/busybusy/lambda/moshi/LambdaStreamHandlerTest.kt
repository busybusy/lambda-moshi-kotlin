package io.busybusy.lambda.moshi

import com.amazonaws.services.lambda.runtime.Context
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream

internal class LambdaStreamHandlerTest {
    @Test
    fun `it handles a data class event and data class result`() {
        val handler = object : LambdaStreamHandler<BasicEvent, BasicResult>() {
            override fun handle(event: BasicEvent, context: Context?) = event.run { BasicResult(foo.toInt(), bar.toString()) }
        }

        @Language("JSON")
        val input = """
            {
                "foo": "100",
                "bar": 200
            }
        """.toByteArray().inputStream()

        val output = ByteArrayOutputStream()
        handler.handleRequest(input, output, null)

        assertEquals("""{"baz":100,"quaz":"200"}""", output.toString())
    }

    data class BasicEvent(val foo: String, val bar: Int)
    data class BasicResult(val baz: Int, val quaz: String)
}