package labeled_tab_separated

import core.*
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class SrcParserTest extends Specification {
    def parse() {
        expect:
        SrcParser.parse([
                'key-1 : value 1 \t key-2 : value 2',
                'key-1 : value 3 \t key-2 : value 4'
        ]) == new Data([
                new Record([
                        new Element(new Key(['key', '1']), new Value('value 1')), new Element(new Key(['key', '2']), new Value('value 2')),
                ]),
                new Record([
                        new Element(new Key(['key', '1']), new Value('value 3')), new Element(new Key(['key', '2']), new Value('value 4'))
                ])
        ])
    }
}
