package ch.yvu.rxpect

import ch.yvu.rxpect.dispose.DisposeExpectationBuilder
import ch.yvu.rxpect.expect.DefaultExpectation
import ch.yvu.rxpect.expect.DefaultExpectationBuilderImpl
import ch.yvu.rxpect.mockito.defaultValueGenerator
import ch.yvu.rxpect.subscribe.SubscribeCompletableExpectationBuilder
import ch.yvu.rxpect.subscribe.SubscribeExpectation
import ch.yvu.rxpect.subscribe.SubscribeMaybeExpectationBuilder
import ch.yvu.rxpect.subscribe.SubscribeObservableExpectationBuilder
import ch.yvu.rxpect.subscribe.SubscribeSingleExpectationBuilder
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

object RXpect {
    fun <T> expectDispose(methodCall: Single<T>?): Expectation =
        DisposeExpectationBuilder.expectDispose(methodCall)

    fun <T> expectDispose(methodCall: Maybe<T>?): Expectation =
        DisposeExpectationBuilder.expectDispose(methodCall)

    fun <T> expectDispose(methodCall: Observable<T>?): Expectation =
        DisposeExpectationBuilder.expectDispose(methodCall)

    fun expectDispose(methodCall: Completable?): Expectation =
        DisposeExpectationBuilder.expectDispose(methodCall)

    fun <T> expectSubscribe(methodCall: Observable<T>?, count: Int = 1): SubscribeExpectation<T> =
        SubscribeObservableExpectationBuilder(methodCall, null, count).build()

    fun <T> expectSubscribe(methodCall: Maybe<T>?, count: Int = 1): SubscribeExpectation<T> =
        SubscribeMaybeExpectationBuilder(methodCall, null, count).build()

    fun expectSubscribe(methodCall: Completable?, count: Int = 1): Expectation =
        SubscribeCompletableExpectationBuilder(methodCall, count).build()

    inline fun <reified T : Any> expectSubscribe(methodCall: Single<T>?, count: Int = 1): SubscribeExpectation<T> =
        SubscribeSingleExpectationBuilder(
            methodCall,
            defaultValueGenerator("Please provide a return value as follows expectSubscribe(mock.foo()).thenReturn(returnValue)"),
            count
        ).build()

    inline fun <reified T> expect(methodCall: T, count: Int = 1): DefaultExpectation<T> =
        DefaultExpectationBuilderImpl(
            methodCall,
            defaultValueGenerator("Please provide a return value as follows expect(mock.foo()).thenReturn(returnValue)"),
            count
        ).build()
}