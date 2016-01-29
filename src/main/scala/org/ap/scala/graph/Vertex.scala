package org.ap.scala.graph

/**
 * Objects of this class hold value of different properties of an edge e.g. distance between 2 vertices, cost of travel between 2 vertices etc.
 */
class Vertex(val name:String = Vertex.createName) {
  val edges = scala.collection.mutable.Map[Vertex, Double]()
  
  def join(v:Vertex, value:Double = 0): Edge =  {
    edges += (v -> value) 
    v.edges += (this -> value)
    new Edge(this, v, value)
  }
  
  def distanceTo(v:Vertex):Double = {
    if (v == this) 0 else edges.get(v).getOrElse(-1)
  }
  
  override def toString():String = {
      name  
    }
}

object Vertex {

  var count = 0
  def createName = {
    count += 1
    "v" + count
  }

}