package epfl.goban.board

abstract class Tile {
  def isEmpty: Boolean = false
}

abstract class Stone extends Tile {
  def complimentary: Stone
}

case object Black extends Stone {
  def complimentary: Stone = White
}

case object White extends Stone {
  def complimentary: Stone = Black
}

case object Empty extends Tile {
  override def isEmpty: Boolean = true
}

sealed trait Board
abstract class GridBoard extends Board {
  def width: Int
  def height: Int

  def apply(x: Int, y: Int, color: Stone): Board
}

class IntegratedBoard(private val tiles: Seq[Seq[Tile]]) extends GridBoard {
  val width = tiles size
  val height = tiles(0) size
  
  /**
   * Splits the tiles in four, adds the tile in between, and then return the result.
   */
  def updateTiles(x: Int, y: Int, tile: Tile): Seq[Seq[Tile]] =
    (tiles.take(y) :+ ((tiles(y).take(x) :+ tile) ++: tiles(y).drop(x + 1))) ++: tiles.drop(y + 1)
  
  /**
   * Adds a stone on the board.
   */
  def apply(x: Int, y: Int, color: Stone): IntegratedBoard = {
    if (!tiles(x)(y).isEmpty) this
    new IntegratedBoard(newBoardAfterCapture(x, y, color))
  }
  
  /**
   * Returns a new board after the capture algorithm has been applied
   */
  def newBoardAfterCapture(x: Int, y: Int, color: Stone): Seq[Seq[Tile]] = {
    def lookForLiberties(pos: (Int,Int), allyStone: Stone, checked: Set[(Int,Int)]): Boolean = {
      // redifining horizontal position (hPos = x) and vertical position (vPos = y)
      val hPos = pos._1
      val vPos = pos._2
      if ((tiles(hPos+1)(vPos) isEmpty) ||
          (tiles(hPos)(vPos+1) isEmpty) ||
          (tiles(hPos-1)(vPos) isEmpty) ||
          (tiles(hPos)(vPos-1) isEmpty))   {
        true
      }
      else {
        (!checked.exists(posTuple => (posTuple._1 == hPos+1 && posTuple._2 == vPos)) && tiles(hPos+1)(vPos) == allyStone && lookForLiberties((hPos+1,vPos),allyStone,checked + pos)) ||
        (!checked.exists(posTuple => (posTuple._1 == hPos && posTuple._2 == vPos+1)) && tiles(hPos)(vPos+1) == allyStone && lookForLiberties((hPos,vPos+1),allyStone,checked + pos)) ||
        (!checked.exists(posTuple => (posTuple._1 == hPos-1 && posTuple._2 == vPos)) && tiles(hPos-1)(vPos) == allyStone && lookForLiberties((hPos-1,vPos),allyStone,checked + pos)) ||
        (!checked.exists(posTuple => (posTuple._1 == hPos && posTuple._2 == vPos-1)) && tiles(hPos)(vPos-1) == allyStone && lookForLiberties((hPos,vPos-1),allyStone,checked + pos))
      }
    }
    
    if (lookForLiberties((x+1,y),color complimentary, Set()) &&
        lookForLiberties((x,y+1),color complimentary, Set()) &&
        lookForLiberties((x-1,y),color complimentary, Set()) &&
        lookForLiberties((x,y-1),color complimentary, Set()))   {
      updateTiles(x,y,color)
    }
    else updateTiles(x,y,color)
  }
}