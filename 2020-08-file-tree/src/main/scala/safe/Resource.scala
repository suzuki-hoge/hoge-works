package safe

sealed trait Resource {
  def show(indent: Int = 0): String
}

case class Directory(path: Path, subs: Seq[Resource]) extends Resource {
  override def show(indent: Int): String = {
    if (subs.isEmpty) s"${"  " * indent}${path.name}"
    else s"${"  " * indent}${path.name}\n${subs.map(sub => sub.show(indent + 1)).mkString("\n")}"
  }
}

case class File(path: Path) extends Resource {
  override def show(indent: Int): String = s"${"  " * indent}${path.name}"
}

case class Path(root: String, own: String) {
  def name: String = {
    if (root == own) "."
    else own.replaceFirst(s"$root/", "").split("/").last
  }
}
