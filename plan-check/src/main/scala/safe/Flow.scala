package safe

import safe.domain.Plans
import safe.message.Line
import unsafe.Parser

object Flow {
  def apply(in: String): String = {
    Parser.planName(in)
      .map(Plans.get)
      .map(Line)
      .fold(_ => "不正なプラン名です。", _.show)
  }
}
