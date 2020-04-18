package wp.unsafe

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class ProcessTest extends Specification {
    def apply() {
        expect:
        Process.apply(args) == exp

        where:
        args                                                                || exp
        ['this book is funny', '--capitalize', '--reverse', '--horizontal'] || 'Funny Is Book This'
        ['', '--capitalize', '--horizontal']                                || ''
        ['this book is funny', '--capitalize', '--reverse']                 || 'error: no unique format option.'
        ['this book is funny', '--horizontal']                              || 'error: no convert option.'
        ['this book', 'is funny', '--capitalize', '--horizontal']           || 'error: no unique arg.'
        ['--capitalize', '--horizontal']                                    || 'error: no unique arg.'
    }
}
