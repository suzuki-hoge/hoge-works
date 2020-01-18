package safe.domain

object Plans {
  def get(name: PlanName): Plan = name match {
    case Small => Plan(Small, PlanPrice(3000), PlanSize(1))
    case Normal => Plan(Normal, PlanPrice(5000), PlanSize(3))
    case Large => Plan(Large, PlanPrice(7000), PlanSize(5))
  }
}

case class Plan(name: PlanName, price: PlanPrice, size: PlanSize)

sealed trait PlanName

object Small extends PlanName

object Normal extends PlanName

object Large extends PlanName

case class PlanPrice(v: Int)

case class PlanSize(v: Int)
