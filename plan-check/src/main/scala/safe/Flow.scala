package safe

import safe.domain.Plans
import safe.message.Line
import unsafe.Parser

object Flow {
  def apply(plan: String, user: String): String = (
    for {
      plan <- Parser.planName(plan).left.map(_ => "不正なプラン名です。")
      user <- Parser.userType(user).left.map(_ => "不正なタイプです。")
    } yield Plans.get(plan, user)
    ).map(Line).fold(identity, _.show)
}
