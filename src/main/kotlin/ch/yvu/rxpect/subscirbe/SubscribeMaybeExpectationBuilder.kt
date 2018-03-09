package ch.yvu.rxpect.subscirbe

import ch.yvu.rxpect.Expectation
import ch.yvu.rxpect.buildExpectation
import io.reactivex.Observable
import org.mockito.stubbing.OngoingStubbing

fun <T> OngoingStubbing<Observable<T>>.thenEmit(value: T): SubscribeExpectationBuilder<T> =
    SubscribeObservableExpectationBuilder(this, value)

fun <T> OngoingStubbing<Observable<T>>.thenEmpty(): SubscribeExpectationBuilder<T> =
    SubscribeObservableExpectationBuilder(this, null)

class SubscribeObservableExpectationBuilder<T>(
    private val ongoingStubbing: OngoingStubbing<Observable<T>>,
    private val value: T?
) : SubscribeExpectationBuilder<T> {

    override fun build(): Expectation =
        buildExpectation(ongoingStubbing) { expectation ->
            {
                if (value != null) {
                    Observable.just(value).doOnSubscribe { expectation.fulfilled() }
                } else {
                    Observable.empty()
                }
            }
        }
}