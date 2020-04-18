package wp.safe.core

import spock.lang.Specification

class WordsTest extends Specification {
    def map() {
        expect:
        new Words(vs).map { w -> w.capitalize() }.vs == exp

        where:
        vs                                 || exp
        [new Word('hi'), new Word('jack')] || [new Word('Hi'), new Word('Jack')]
    }

    def reverse() {
        setup:
        def sut = new Words(vs)

        expect:
        sut.reverse().vs == exp
        sut.vs != exp

        where:
        vs                                 || exp
        [new Word('hi'), new Word('jack')] || [new Word('jack'), new Word('hi')]
    }
}
