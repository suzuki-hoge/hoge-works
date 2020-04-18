package wp.unsafe

import spock.lang.Specification
import spock.lang.Unroll
import wp.safe.convert.Capitalize
import wp.safe.convert.Reverse
import wp.safe.core.Word
import wp.safe.core.Words
import wp.safe.format.Horizontal

@Unroll
class InputTest extends Specification {
    def converters() {
        expect:
        Input.converters(args) == exp

        where:
        args                                            || exp
        [new Arg('--capitalize')]                       || Parsed.success([new Capitalize()])
        [new Arg('--capitalize'), new Arg('--reverse')] || Parsed.success([new Capitalize(), new Reverse()])
        [new Arg('--horizontal')]                       || Parsed.failure('no convert option.')
        []                                              || Parsed.failure('no convert option.')
    }

    def formatter() {
        expect:
        Input.formatter(args) == exp

        where:
        args                                             || exp
        [new Arg('--horizontal')]                        || Parsed.success(new Horizontal())
        [new Arg('--horizontal'), new Arg('--vertical')] || Parsed.failure('no unique format option.')
        [new Arg('--capitalize')]                        || Parsed.failure('no unique format option.')
        []                                               || Parsed.failure('no unique format option.')
    }

    def words() {
        expect:
        Input.words(args) == exp

        where:
        args                                      || exp
        [new Arg('hi john')]                      || Parsed.success(new Words([new Word('hi'), new Word('john')]))
        [new Arg('hi john'), new Arg('hey jane')] || Parsed.failure('no unique arg.')
        [new Arg('')]                             || Parsed.success(new Words([]))
        []                                        || Parsed.failure('no unique arg.')
    }
}
