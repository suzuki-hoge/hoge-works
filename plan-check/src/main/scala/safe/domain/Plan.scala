package safe.domain

object Plans {
  def get(name: PlanName, user: UserType): Plan = (name, user) match {
    case (Small, Basic) => Plan(Small, PlanPrice(3000), PlanSize(1), user)
    case (Normal, Basic) => Plan(Normal, PlanPrice(5000), PlanSize(3), user)
    case (Large, Basic) => Plan(Large, PlanPrice(7000), PlanSize(5), user)
    case (Small, Premium) => Plan(Small, PlanPrice(2000), PlanSize(1), user)
    case (Normal, Premium) => Plan(Normal, PlanPrice(4000), PlanSize(3), user)
    case (Large, Premium) => Plan(Large, PlanPrice(6000), PlanSize(5), user)
  }
}

case class Plan(name: PlanName, price: PlanPrice, size: PlanSize, user: UserType)

sealed trait PlanName

object Small extends PlanName

object Normal extends PlanName

object Large extends PlanName

case class PlanPrice(v: Int)

case class PlanSize(v: Int)
