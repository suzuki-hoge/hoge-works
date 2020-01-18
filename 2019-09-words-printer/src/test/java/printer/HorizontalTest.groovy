package printer

import spock.lang.Specification

class HorizontalTest extends Specification {
    def print() {
        expect:
        Horizontal.print.print(['this', 'book', 'is', 'so', 'funny']) == 'this book is so funny'
    }
}
