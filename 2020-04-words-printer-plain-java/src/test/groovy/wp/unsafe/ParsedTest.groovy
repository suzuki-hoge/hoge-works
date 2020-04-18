package wp.unsafe

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class ParsedTest extends Specification {
    def map() {
        expect:
        p.map { n -> "value: " + n } == exp

        where:
        p                       || exp
        Parsed.success(1)       || Parsed.success('value: 1')
        Parsed.failure('error') || Parsed.failure('error')
    }

    def flatMap() {
        expect:
        p1.flatMap { v1 -> p2.map { v2 -> v1 * v2 } } == exp

        where:
        p1                        | p2                        || exp
        Parsed.success('#')       | Parsed.success(3)         || Parsed.success('###')
        Parsed.success('#')       | Parsed.failure('error 2') || Parsed.failure('error 2')
        Parsed.failure('error 1') | Parsed.success(3)         || Parsed.failure('error 1')
        Parsed.failure('error 1') | Parsed.failure('error 2') || Parsed.failure('error 1')
    }

    def fold() {
        expect:
        p.fold({ n -> 'value: ' + n }, { e -> e }) == exp

        where:
        p                       || exp
        Parsed.success(1)       || 'value: 1'
        Parsed.failure('error') || 'error'
    }
}
