package blocks

import core.*
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class DstParserTest extends Specification {
    def parse() {
        expect:
        DstParser.parse(
                new Data([
                        new Record([
                                new Element(new Key(['order', 'id']), new Value('1')), new Element(new Key(['item', 'name']), new Value('foo')),
                        ]),
                        new Record([
                                new Element(new Key(['order', 'id']), new Value('2')), new Element(new Key(['item', 'name']), new Value('bar')),
                        ])
                ])
        ) == [
                'orderId=1',
                'itemName=foo',
                '',
                'orderId=2',
                'itemName=bar'
        ]
    }
}
