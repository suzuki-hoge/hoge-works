package parser

import spock.lang.Specification

class CapitalizeTest extends Specification {
    def parse() {
        expect:
        Capitalize.parse.parse(['this', 'book', 'is', 'so', 'funny']) == ['This', 'Book', 'Is', 'So', 'Funny']
    }
}
