package safe

sealed trait ResourceType

object All extends ResourceType

object DirectoryOnly extends ResourceType
