//NBD Ćwiczenia 1 – Scala
object NBD01 {
  def main(args: Array[String]) = {

    val daysList = List("Poniedzialek", "Wtorek", "Sroda", "Czwartek", "Piatek", "Sobota", "Niedziela")

//Podpunkt 1
    var days = ""
    var daysP = ""
    var daysWhile = ""

    //1a
    for (x <- daysList)
      days = days + x + ","
    days = days.dropRight(1)

    //1b
    for (x <- daysList)
      if (x.startsWith("P"))
        daysP = daysP + x + ","
    daysP = daysP.dropRight(1)

    //1c
    var x = 0
    var s = daysList.size
    while (x < s) {
      daysWhile = daysWhile + daysList(x) + ","
      x += 1
    }
    daysWhile = daysWhile.dropRight(1)

    println(s"Podpunkt 1a:\t$days\nPodpunkt 1b:\t$daysP\nPodpunkt 1c:\t$daysWhile")

//Podpunkt 2

    //2a
    def daysRecursion(index: Int = 0): String = {
      var str = daysList(index)
      if (index == daysList.size - 1)
        str
      else
        str = str + "," + daysRecursion(index + 1)
      str
    }

    //2b
    def daysRecursionReverted(index: Int = daysList.size - 1): String = {
      var str = daysList(index)
      if (index == 0)
        str
      else
        str = str + "," + daysRecursionReverted(index - 1)
      str

    }

    println("Podpunkt 2a: \t" + daysRecursion())
    println("Podpunkt 2b: \t" + daysRecursionReverted())

//Podpunkt 3
    def daysTailRecursion(str: String = "", index: Int = 0): String = {
      if (index == daysList.size) {
        str
      } else {
        val str2 = str + daysList(index) + ","
        daysTailRecursion(str2, index + 1)
      }
    }

    println("Podpunkt 3:\t" + daysTailRecursion().dropRight(1))

//Podpunkt 4   
    
    //4a
    val daysFoldl = daysList.foldLeft(""){
      (z,f) => z + f + ","
    }
   
    
    //4b
    val daysFoldr = daysList.foldRight(""){
      (z,f) => z + "," + f
    }
  
    
    //4c
    val daysFoldlP = daysList.foldLeft(""){
     (z,f) => 
     if(z.startsWith("P"))
       z + f +""
     else ""
    }

    println(s"Podpunkt 4a:\t$daysFoldl\nPodpunkt 4b:\t$daysFoldr\nPodpunkt 4c:\t$daysFoldlP")
    
    
//Podpunkt 5
    val productPrices = Map("jablko" -> 2, "gruszka" -> 3, "sliwka" -> 4)
    val productPricesDiscount = productPrices map { case (a, b) => a -> 0.9 * b }
    println(s"Podpunkt 5:\n$productPrices\n$productPricesDiscount")
    
    
//Podpunkt 6
    def printTuple(x : (Int, String, Double)){
      println(x.toString)
    }
    val t = Tuple3(10, "whatever", 10.33)
    println("Podpunkt 6:\t") 
    printTuple(t)
    
//Podpunkt 7
    val exampleMap = Map(1 -> "Złoto", 2 -> "Srebro", 3 -> "Brąz")    
    val result1 = exampleMap.get(1);
    val result2 = exampleMap.get(4);
    
    println(s"Podpunkt 7:\n$result1\n$result2")
    
//Podpunkt 8
    val integersList = List(-1, -5, 0, 5, 0, 10, 0)
    
    def removeZerosFromList(list: List[Int]) : List[Int] = list match {
      case Nil => Nil
      case h :: t =>
        if (h == 0)
          removeZerosFromList(t)
        else
          h :: removeZerosFromList(t)
    }
    println(s"Podpunkt 8:\n$integersList\n" + removeZerosFromList(integersList))
    
//Podpunkt 9
    def addPlus1ToList(list: List[Int]) : List[Int] = list map {
      case(a) => a +1
    }
    val integersListPlus1 = addPlus1ToList(integersList)
    println(s"Podpunkt 9:\n$integersList\n$integersListPlus1")
    
//Podpunkt 10
    val integersList2 = List(-15, -10, -6, -5, -4, -3, -2, 0, 0, 10, 12, 15)
    def returnAbsoluteValues(list: List[Int]) : List[Int] = {
      list.filter(_>=(-5))
      .filter(_<=12)
      .map((x: Int) => if(x < 0) -x else x)
    }
    println(s"Podpunkt 10:\n$integersList2\n" + returnAbsoluteValues(integersList2))
     
  }
}
