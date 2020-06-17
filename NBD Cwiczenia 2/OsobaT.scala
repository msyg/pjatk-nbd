class OsobaT(val firstName : String , val lastName : String ) {
    private var _tax = 0.0
    def tax = _tax
}   

trait Pracownik extends OsobaT{
    var salary : Double = 0
    override def tax = 0.2 * salary
  }
   
trait Student extends OsobaT {
    override def tax = 0.0
  }
  
trait Nauczyciel extends Pracownik{
    override def tax = 0.1 * salary
  }
