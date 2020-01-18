package printer

import spock.lang.Specification

class VerticalTest extends Specification {
    def print() {
        expect:
        Vertical.print.print(['this', 'book', 'is', 'so', 'funny']) == '''\
this
book
is
so
funny'''
    }
}
