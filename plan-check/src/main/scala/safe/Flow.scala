package safe

import safe.domain.Plans
import safe.message.Line
import unsafe.Parser

object Flow {
  def apply(name: String, user: String): String = (
    for {
      name <- Parser.planName(name).left.map(_ => "不正なプラン名です。")
      user <- Parser.userType(user).left.map(_ => "不正なタイプです。")
      plan <- Plans.get(name, user).left.map(_ => "不正な組み合わせです。")
    } yield plan
    ).map(Line).fold(identity, _.show)
}
