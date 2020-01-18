package comma_separated

import core.Key
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class SrcKeysParserTest extends Specification {
    def parse() {
        expect:
        SrcKeysParser.parse('key 1, key 2') == [new Key(['key', '1']), new Key(['key', '2'])]
    }
}
