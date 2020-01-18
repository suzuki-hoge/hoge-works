package safe.message

import safe.domain._

case class Line(plan: Plan) {
  def show: String = s"変更しました。　${show(plan.user)}会員 の方は ${show(plan.current)}プラン を 毎月 ${show(plan.price)} 円 で ${show(plan.size)} GB ご利用できます。"

  private def show(user:UserType): String = user match {
    case Basic => "ベーシック"
    case Premium => "プレミアム"
  }

  private def show(name: PlanName): String = name match {
    case Small => "スモール"
    case Normal => "ノーマル"
    case Large => "ラージ"
    case Mega => "メガ"
  }

  private def show(price: PlanPrice): String = price.v.toString.replace("000", ",000")

  private def show(size: PlanSize): String = size.v.toString
}
