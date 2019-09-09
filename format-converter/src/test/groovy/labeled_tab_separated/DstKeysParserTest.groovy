package labeled_tab_separated


import core.Element
import core.Key
import core.Record
import core.Value
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class DstKeysParserTest extends Specification {
    def parse() {
        expect:
        DstKeysParser.parse(
                new Record([
                        new Element(new Key(['key', '1']), new Value('value 1')), new Element(new Key(['key', '2']), new Value('value 2'))
                ])
        ) == ['key-1', 'key-2']
    }
}
