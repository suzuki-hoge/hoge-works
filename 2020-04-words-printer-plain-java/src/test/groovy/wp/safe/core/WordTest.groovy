package wp.safe.core

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class WordTest extends Specification {
    def capitalize() {
        expect:
        new Word(s).capitalize().v == exp

        where:
        s      || exp
        'book' || 'Book'
    }

    def compress() {
        expect:
        new Word(s).compress().v == exp

        where:
        s      || exp
        'book' || 'bok'
        'pen'  || 'pen'
    }
}
