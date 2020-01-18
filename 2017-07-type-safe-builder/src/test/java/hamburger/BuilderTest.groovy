package hamburger

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class BuilderTest extends Specification {
    def test() {
        expect:
        fixture == exp

        where:
        //@formatter:off
        fixture                                        || exp
        Fixture.てりやき_オレンジ                      || Builder.init().てりやき().オレンジ().なし().build()
        Fixture.チーズ_コーヒー_ミルク_ポテト_ナゲット || Builder.init().チーズ().コーヒー('ミルク').サイド('ポテト', 'ナゲット').build()
        //@formatter:on
    }
}
