package ch.yvu.rxpect.expect

import ch.yvu.rxpect.setupExpectation
import com.nhaarman.mockitokotlin2.whenever

class DefaultExpectationBuilderImpl<T>(
    private val methodCall: T,
    private val defaultAnswer: () -> T,
    override val count: Int
) : DefaultExpectationBuilder<T> {

    private var answer: () -> T =
        defaultAnswer

    override fun returnValue(value: T) {
        this.answer = { value }
    }

    override fun build(): DefaultExpectation<T> =
        setupExpectation(DefaultExpectation(this), whenever(methodCall)) { expectation ->
            {
                expectation.fulfilled()
                answer()
            }
        }
}