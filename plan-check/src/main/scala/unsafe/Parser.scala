package unsafe

import safe.domain.{Large, Normal, PlanName, Small}

object Parser {
  def planName(s: String): Either[String, PlanName] = s match {
    case "small" => Right(Small)
    case "normal" => Right(Normal)
    case "large" => Right(Large)
    case _ => Left("invalid name")
  }
}
