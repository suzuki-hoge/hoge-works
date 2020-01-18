package blocks


import core.Element
import core.Key
import core.Record
import core.Value
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class DstValuesParserTest extends Specification {
    def parse() {
        expect:
        DstValuesParser.parse(
                new Record([
                        new Element(new Key(['order', 'id', '1']), new Value('value 1')), new Element(new Key(['order', 'id', '2']), new Value('value 2'))
                ])
        ) == ['value 1', 'value 2']
    }
}
