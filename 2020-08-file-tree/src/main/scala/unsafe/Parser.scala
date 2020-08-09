package unsafe

import safe.{All, DirectoryOnly, ResourceType}

object Parser {
  def apply(line: String): Either[String, (String, ResourceType)] = {
    line.split(" ").toSeq match {
      case Seq(s) => Right((s, All))
      case Seq(s, "-type", "d") => Right((s, DirectoryOnly))
      case Seq(s, "-type", "a") => Right((s, All))
      case _ => Left("invalid input.")
    }
  }
}
