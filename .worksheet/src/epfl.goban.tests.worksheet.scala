package epfl.goban.tests

object worksheet {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(88); 
  println("Welcome to the Scala worksheet")
  
  abstract class Stone
  case object White extends Stone
  case object Black extends Stone
  case object Empty extends Stone;$skip(215); 
  
  val test: Seq[Seq[Stone]] = List(List(Black,White,Black),List(Black,Empty,Empty));System.out.println("""test  : Seq[Seq[epfl.goban.tests.worksheet.Stone]] = """ + $show(test ));$skip(19); 
  
  val color = 0;System.out.println("""color  : Int = """ + $show(color ));$skip(12); 
  val x = 1;System.out.println("""x  : Int = """ + $show(x ));$skip(12); 
  val y = 2;System.out.println("""y  : Int = """ + $show(y ));$skip(164); 
  
  //test.map(line => line.map(cell => if (line == y && cell == x) color else cell)))
  
  
  val rawr: Seq[Seq[Int]] = List(List(1,2,3),List(4,5,6),List(7,8,9));System.out.println("""rawr  : Seq[Seq[Int]] = """ + $show(rawr ));$skip(73); val res$0 = 
  
  rawr map (line => line map (cell => if (cell == line) 0 else cell));System.out.println("""res0: Seq[Seq[Int]] = """ + $show(res$0));$skip(16); val res$1 = 

  rawr.drop(1);System.out.println("""res1: Seq[Seq[Int]] = """ + $show(res$1));$skip(90); val res$2 = 
  (rawr.take(y) :+ ((rawr(y).take(x) :+ color) ++: rawr(y).drop(x+1))) ++: rawr.drop(y+1);System.out.println("""res2: Seq[Seq[Int]] = """ + $show(res$2))}
}