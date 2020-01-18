package unsafe

import safe.domain._

object Parser {
  def planName(s: String): Either[String, PlanName] = s match {
    case "small" => Right(Small)
    case "normal" => Right(Normal)
    case "large" => Right(Large)
    case _ => Left("invalid name")
  }

  def userType(s: String): Either[String, UserType] = s match {
    case "basic" => Right(Basic)
    case "premium" => Right(Premium)
    case _ => Left("invalid type")
  }
}
