package ch.yvu.rxpect.subscribe

import ch.yvu.rxpect.setupExpectation
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable

class SubscribeObservableExpectationBuilder<T>(
    private val methodCall: Observable<T>?,
    private val defaultValue: T?,
    override val count: Int
) : SubscribeExpectationBuilder<T> {

    private var value: T? = defaultValue

    override fun emittedValue(value: T) {
        this.value = value
    }

    override fun build(): SubscribeExpectation<T> =
        setupExpectation(SubscribeExpectation(this), whenever(methodCall)) { expectation ->
            {
                value.let {
                    val observable =
                        if (it != null) {
                            Observable.just(it)
                        } else {
                            Observable.empty()
                        }
                    observable.doOnSubscribe { expectation.fulfilled() }
                }
            }
        }
}