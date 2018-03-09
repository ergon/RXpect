package ch.yvu.rxpect.subscirbe

import ch.yvu.rxpect.Expectation
import ch.yvu.rxpect.ExpectationWithLatch
import io.reactivex.Maybe
import org.mockito.stubbing.OngoingStubbing

fun <T> OngoingStubbing<Maybe<T>>.thenEmit(value: T): SubscribeExpectationBuilder<T> =
    SubscribeMaybeExpectationBuilder(this, value)

fun <T> OngoingStubbing<Maybe<T>>.thenEmpty(): SubscribeExpectationBuilder<T> =
    SubscribeMaybeExpectationBuilder(this, null)

class SubscribeMaybeExpectationBuilder<T>(
    private val ongoingStubbing: OngoingStubbing<Maybe<T>>,
    private val value: T?
) : SubscribeExpectationBuilder<T> {
    override fun build(): Expectation {
        val expectation = ExpectationWithLatch()
        ongoingStubbing.thenAnswer {
            if (value != null) {
                Maybe.just(value).doOnSubscribe { expectation.fulfilled() }
            } else {
                Maybe.empty()
            }
        }

        return expectation
    }
}