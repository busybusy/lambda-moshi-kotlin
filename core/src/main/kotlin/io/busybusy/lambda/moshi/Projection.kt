package io.busybusy.lambda.moshi

import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.allSupertypes
import kotlin.reflect.jvm.jvmErasure

fun Any.supertypeArguments(target: KClass<*>): List<KType> = this::class.allSupertypes.first { it.jvmErasure == target }.arguments.map { it.type!! }