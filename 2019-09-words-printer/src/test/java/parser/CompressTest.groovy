package parser

import spock.lang.Specification

class CompressTest extends Specification {
    def parse() {
        expect:
        Compress.parse.parse(['this', 'book', 'is', 'so', 'funny']) == ['this', 'bok', 'is', 'so', 'funy']
    }
}
