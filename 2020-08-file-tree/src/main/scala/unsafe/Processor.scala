package unsafe

import java.io

import safe._

object Processor {
  def apply(path: String, rt: ResourceType): Either[String, Resource] = {
    val r = new io.File(path)

    if (r.isDirectory) Right(Directory(Path(path, path), subs(path, r, rt)))
    else if (r.exists) Left(s"$path is not directory.")
    else Left(s"$path is not exists.")
  }

  private def subs(root: String, file: io.File, rt: ResourceType): Seq[Resource] = rt match {
    case All => all(root, file)
    case DirectoryOnly => directoryOnly(root, file)
  }

  private def all(root: String, file: io.File): Seq[Resource] = {
    if (file.isFile) Seq(File(Path(root, file.getAbsolutePath)))
    else file.listFiles.sorted.map { r =>
      if (r.isFile) File(Path(root, r.getAbsolutePath))
      else Directory(Path(root, r.getAbsolutePath), all(root, r))
    }
  }

  private def directoryOnly(root: String, file: io.File): Seq[Resource] = {
    if (file.isFile) Seq()
    else file.listFiles.sorted.flatMap { r =>
      if (r.isFile) Seq()
      else Seq(Directory(Path(root, r.getAbsolutePath), directoryOnly(root, r)))
    }
  }
}
