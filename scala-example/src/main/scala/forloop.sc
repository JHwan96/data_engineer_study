val nums: List[Int] = List(1,2,3,4,5)

for (num <- nums){
  println(num)
}
nums.foreach(it => print(it+","))

val numberMap = Map(
  "one" -> 1,
  "two" -> 2,
  "three" -> 3
)

for ((key, value) <- numberMap){
  println(s"Number $key : $value x 10 = ${value*10}")
}


val doubleNumbers = for (num <- nums) num*2
println(doubleNumbers)

val doubleNumbersWithYield = for (num <- nums) yield num*2
println(doubleNumbersWithYield)

val someNumbers = for(num<-nums) yield{
  val numPlusOne = num+1
  val numMinusOne = num - 1
  numPlusOne * numMinusOne
}