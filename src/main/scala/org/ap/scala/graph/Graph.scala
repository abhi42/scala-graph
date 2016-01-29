package org.ap.scala.graph

class Graph() {
  val vertices = scala.collection.mutable.Set[Vertex]() 
  
  def addVertex(v:Vertex) {
    vertices += v
  }
  
  def createVertex(name:String = Vertex.createName): Vertex = {
    val v = new Vertex(name)
    addVertex(v)
    v
  }
}