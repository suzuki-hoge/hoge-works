package blocks

import core.Value
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class SrcValuesParserTest extends Specification {
    def parse() {
        expect:
        SrcValuesParser.parse('orderId1=1') == new Value('1')
    }
}
