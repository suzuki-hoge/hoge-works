package safe.domain

object Plans {
  def get(name: PlanName, user: UserType): Either[String, Plan] = (name, user) match {
    case (Small, Basic) => Right(Plan(Small, PlanPrice(3000), PlanSize(1), user))
    case (Normal, Basic) => Right(Plan(Normal, PlanPrice(5000), PlanSize(3), user))
    case (Large, Basic) => Right(Plan(Large, PlanPrice(7000), PlanSize(5), user))
    case (Mega, Basic) => Left("invalid pair")
    case (Small, Premium) => Right(Plan(Small, PlanPrice(2000), PlanSize(1), user))
    case (Normal, Premium) => Right(Plan(Normal, PlanPrice(4000), PlanSize(3), user))
    case (Large, Premium) => Right(Plan(Large, PlanPrice(6000), PlanSize(5), user))
    case (Mega, Premium) => Right(Plan(Mega, PlanPrice(8000), PlanSize(7), user))
  }
}

case class Plan(name: PlanName, price: PlanPrice, size: PlanSize, user: UserType)

sealed trait PlanName

object Small extends PlanName

object Normal extends PlanName

object Large extends PlanName

object Mega extends PlanName

case class PlanPrice(v: Int)

case class PlanSize(v: Int)
