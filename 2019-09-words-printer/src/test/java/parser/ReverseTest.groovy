package parser

import spock.lang.Specification

class ReverseTest extends Specification {
    def parse() {
        expect:
        Reverse.parse.parse(['this', 'book', 'is', 'so', 'funny']) == ['funny', 'so', 'is', 'book', 'this']
    }
}
