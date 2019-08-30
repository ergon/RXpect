package ch.yvu.rxpect.subscribe

import ch.yvu.rxpect.ExpectationBuilder

interface SubscribeExpectationBuilder<T> : ExpectationBuilder<SubscribeExpectation<T>> {
    val count: Int
    fun emittedValue(value: T)
}