package safe.message

import safe.domain._

case class Line(plan: Plan) {
  def show: String = s"${show(plan.name)}プラン は 毎月 ${show(plan.price)} 円 で ${show(plan.size)} GB ご利用できます。"

  private def show(name: PlanName): String = name match {
    case Small => "スモール"
    case Normal => "ノーマル"
    case Large => "ラージ"
  }

  private def show(price: PlanPrice): String = price.v.toString.replace("000", ",000")

  private def show(size: PlanSize): String = size.v.toString
}
