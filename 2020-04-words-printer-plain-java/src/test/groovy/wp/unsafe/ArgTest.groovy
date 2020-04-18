package wp.unsafe

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class ArgTest extends Specification {
    def notOption() {
        expect:
        new Arg(s).notOption() == exp

        where:
        s       || exp
        'foo'   || true
        '-f'    || true
        '--foo' || false
    }

    def isOption() {
        expect:
        new Arg('--foo').isOption(s) == exp

        where:
        s     || exp
        'foo' || true
        'bar' || false
    }
}
