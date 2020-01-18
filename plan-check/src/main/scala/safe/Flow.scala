package safe

import safe.domain.Plans
import safe.message.Line
import unsafe.Parser

object Flow {
  def apply(current: String, user: String, next: String): String = (
    for {
      current <- Parser.planName(current).left.map(_ => "不正なプラン名です。")
      user <- Parser.userType(user).left.map(_ => "不正なタイプです。")
      plan <- Plans.get(current, user).left.map(_ => "不正な組み合わせです。")
      next <- Parser.planName(next).left.map(_ => "不正なプラン名です。")
      fixed <- plan.change(next).left.map {
        case "small is not allowed" => "スモールプランへの変更はできません。"
        case "same plan" => "同じプランへの変更はできません。"
        case "basic mega is not allowed" => "メガプランはプレミアム会員専用です。"
      }
    } yield fixed
    ).map(Line).fold(identity, _.show)
}
