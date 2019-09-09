package blocks

import core.Key
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class SrcKeysParserTest extends Specification {
    def parse() {
        expect:
        SrcKeysParser.parse('orderId1=1') == new Key(['order', 'id', '1'])
    }
}
