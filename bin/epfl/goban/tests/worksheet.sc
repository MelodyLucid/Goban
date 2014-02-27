package epfl.goban.tests

object worksheet {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  abstract class Stone
  case object White extends Stone
  case object Black extends Stone
  case object Empty extends Stone
  
  val test: Seq[Seq[Stone]] = List(List(Black,White,Black),List(Black,Empty,Empty))
                                                  //> test  : Seq[Seq[epfl.goban.tests.worksheet.Stone]] = List(List(Black, White,
                                                  //|  Black), List(Black, Empty, Empty))
  
  val color = 0                                   //> color  : Int = 0
  val x = 1                                       //> x  : Int = 1
  val y = 2                                       //> y  : Int = 2
  
  //test.map(line => line.map(cell => if (line == y && cell == x) color else cell)))
  
  
  val rawr: Seq[Seq[Int]] = List(List(1,2,3),List(4,5,6),List(7,8,9))
                                                  //> rawr  : Seq[Seq[Int]] = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))
  
  rawr map (line => line map (cell => if (cell == line) 0 else cell))
                                                  //> res0: Seq[Seq[Int]] = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))

  rawr.drop(1)                                    //> res1: Seq[Seq[Int]] = List(List(4, 5, 6), List(7, 8, 9))
  (rawr.take(y) :+ ((rawr(y).take(x) :+ color) ++: rawr(y).drop(x+1))) ++: rawr.drop(y+1)
                                                  //> res2: Seq[Seq[Int]] = List(List(1, 2, 3), List(4, 5, 6), List(7, 0, 9))
}