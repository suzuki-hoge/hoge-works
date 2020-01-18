package labeled_tab_separated

import core.Value
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class SrcValuesParserTest extends Specification {
    def parse() {
        expect:
        SrcValuesParser.parse('key-1 : value 1 \t key-2 : value 2') == [new Value('value 1'), new Value('value 2')]
    }
}
