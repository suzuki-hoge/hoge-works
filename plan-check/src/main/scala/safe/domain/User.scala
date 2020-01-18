package safe.domain

sealed trait UserType

object Basic extends UserType

object Premium extends UserType
