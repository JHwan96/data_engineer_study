try{
  1 / 1
} catch {
  case e: ArithmeticException => println("Are you idiot?")
  case _: Throwable => println("Unknown Exception")
} finally {
  println("print anyway")
}

def upperString(value: String): Option[String] = {
  if (value.isEmpty) None
  else Some(value.toUpperCase)
}

val result1 = upperString(("lowercase"))
val result2 = upperString((""))

if(result1.isDefined) {
  println(result1.get)
}
if(result2.isEmpty) {
  println("empty")
}

def upperString(value: String): Either[String, String] = {
  if (value.isEmpty) Left("Value cannot be empty")
  else Right(value.toUpperCase())
}

val result3 = upperString("lowercase")
val result4 = upperString("")

if(result3.isLeft){
  println(result3.left)
}
else {
  println(result3.right)
}