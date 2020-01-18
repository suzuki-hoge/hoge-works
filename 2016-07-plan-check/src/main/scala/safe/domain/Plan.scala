package safe.domain

object Plans {
  def get(current: PlanName, user: UserType): Either[String, Plan] = (current, user) match {
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

case class Plan(current: PlanName, price: PlanPrice, size: PlanSize, user: UserType) {
  def change(next: PlanName): Either[String, Plan] = (next, user) match {
    case (Small, _) => Left("small is not allowed")
    case (_next, _) if current == _next => Left("same plan")
    case (Mega, Basic) => Left("basic mega is not allowed")
    case _ => Plans.get(next, user)
  }
}

sealed trait PlanName

object Small extends PlanName

object Normal extends PlanName

object Large extends PlanName

object Mega extends PlanName

case class PlanPrice(v: Int)

case class PlanSize(v: Int)
