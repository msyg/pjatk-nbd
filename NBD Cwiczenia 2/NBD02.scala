//NBD Cwiczenia 2 - scala
object NBD02 {
  def main(args: Array[String]): Unit = {
    
//Podpunkt 1   
    def verifyDaysPatternMatching(day: String):String = day match{
      case "Poniedzialek" | "Wtorek" | "Sroda" | "Czwartek" | "Piatek" => "Praca"
      case "Sobota" | "Niedziela" => "Weekend"
      case _ => "Nie ma takiego dnia"
    }
    
    print("Podpunkt 1:\n\t" + verifyDaysPatternMatching("Wtorek") + "\n\t" + verifyDaysPatternMatching("Sobota") 
        + "\n\t" + verifyDaysPatternMatching("Dwudzionek"))    
  
        
//Podpunkt 2
    var konto1 = new KontoBankowe(55)
    var konto2 = new KontoBankowe()
    
    println("\nPodpunkt 2:\n\tPoczątkowy stan konta1: " + konto1.stanKonta +"\n\tWyplata 10: " + konto1.wyplata(10))
    println("\n\tPoczątkowy stan konta2: " + konto2.stanKonta +"\n\tWplata 20: " + konto2.wplata(20))
    
    
//Podpunkt 3
    val osobaJohn = new Osoba("John", "Kowalski")
    val osobaPierre = new Osoba("Pierre", "Nowak")
    val osobaKim = new Osoba("Kim", "Kowalczyk")
    val osobaX = new Osoba("X", "X")
    
    def greeting(o: Osoba):String = o match {
      case Osoba("John",_) => "Hi John!"
      case Osoba(_, "Nowak") => "Hello Mr. Nowak"
      case Osoba("Kim", "Kowalczyk") => "Hello Ms. Kim Kowalczyk"
      case Osoba(_,_) => "Hi Stranger!"
    }
    
    println("Podpunkt 3:\n\t" + greeting(osobaJohn) + "\n\t" + greeting(osobaPierre) + "\n\t" + greeting(osobaKim)
        + "\n\t" + greeting(osobaX))
        
        
//Podpunkt 4
    def functionInt(n: Int) = {
      n*n
    }
    
    def functionWithIntAndFunction(func: Int => Int, n: Int) = func(func(func(n)))
    
    println("Podpunkt 4:\n\t" + functionWithIntAndFunction(functionInt, 2))
    //2*2=4, 4*4=16, 16*16=256
    
    
//Podpunkt 5
    val student = new OsobaT("Jan", "Student") with Student
    val pracownik = new OsobaT("Jan", "Pracownik") with Pracownik
    val nauczyciel = new OsobaT("Jan", "Nauczyciel") with Nauczyciel 
    
    nauczyciel.salary = 1000
    pracownik.salary = 1000
       
    println("Podpunkt 5:\n\tPensja pracownik: " + pracownik.salary + "\n\tPodatek pracownik: " + pracownik.tax) 
    println("\n\tPensja nauczyciel: " + nauczyciel.salary + "\n\tPodatek nauczyciel: " + nauczyciel.tax) 
    println("\n\tPodatek student: " + student.tax)
    
    val studentPracownik = new OsobaT("Jan", "StudentPracownik") with Student with Pracownik
    val pracownikStudent = new OsobaT("Jan", "PracownikStudent") with Pracownik with Student
    
    studentPracownik.salary = 1500
    pracownikStudent.salary = 1500
    
    println("\n\tPensja student-pracownik " + studentPracownik.salary + "\n\tPodatek student-pracownik: "
        + studentPracownik.tax) 
    println("\n\tPensja pracownik-student " + pracownikStudent.salary + "\n\tPodatek pracownik-student: "
        + pracownikStudent.tax) 
    
 }
  
  class KontoBankowe() {
   private var _stanKonta = 0
   
   def this(n: Int){
     this()
     this._stanKonta = n
   }
   
   def stanKonta = _stanKonta
   def wplata(n: Int) = stanKonta + n
   def wyplata(n: Int) = stanKonta - n
  }
  
  case class Osoba(var firstName: String, var lastName: String) {  
  }
  
}
  




