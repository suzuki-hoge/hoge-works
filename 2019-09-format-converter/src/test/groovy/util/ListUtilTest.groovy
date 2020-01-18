package util

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class ListUtilTest extends Specification {
    def split() {
        expect:
        ListUtil.split('a, b ,c , d', ',') == ['a', ' b ', 'c ', ' d']
    }

    def splitAndTrim() {
        expect:
        ListUtil.splitAndTrim('a, b ,c , d', ',') == ['a', 'b', 'c', 'd']
    }

    def head() {
        expect:
        ListUtil.head([1, 2, 3]) == 1
    }

    def tail() {
        expect:
        ListUtil.tail(ns) == exp

        where:
        ns        || exp
        [1, 2, 3] || [2, 3]
        [1]       || []
        []        || []
    }

    def init() {
        expect:
        ListUtil.init(ns) == exp

        where:
        ns        || exp
        [1, 2, 3] || [1]
        [1]       || [1]
        []        || []
    }

    def blocked() {
        expect:
        ListUtil.blocked(['1', '2', '-', '3', '-', '4'], '-') == [['1', '2'], ['3'], ['4']]
    }

    def join() {
        expect:
        ListUtil.join([[1, 2, 3], [4, 5]], 0) == [1, 2, 3, 0, 4, 5]
    }
}
