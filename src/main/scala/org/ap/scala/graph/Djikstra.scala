package org.ap.scala.graph

import scala.collection.mutable.Stack


object Djikstra {
  
  private class ComputeInfo(val v:Vertex, var valueFromSource:Double = Double.PositiveInfinity, var isVisited:Boolean = false, var neighbourOnShortestPath:Vertex = null) {
    
    override def toString():String = {
      val valFromSourceStr = if (valueFromSource == Double.PositiveInfinity) "" else "; value from source: " + valueFromSource
      v.name + valFromSourceStr + "; isVisited: " + isVisited + "; neighbour on shortest path: " + neighbourOnShortestPath + "\n"
    }
  }
  
  private val infos = scala.collection.mutable.MutableList[ComputeInfo]()
  
  def shortestPathBetween(graph:Graph, v1:Vertex, v2:Vertex): ShortestPath = {
    doInit(graph, v1)
    process(v1, v2)
    return buildShortestPath(v1, v2)
  }
  
  private def buildShortestPath(source:Vertex, target:Vertex):ShortestPath = {
    val stack = Stack[Vertex](target)
    
    val info = getInfoForVertex(target)
    var next = target
    while (next != source) {
      next = handleVertexWhileBuildingShortestPath(next, stack)
    }
    return new ShortestPath(stack.elems, info.valueFromSource)
  }
  
  private def handleVertexWhileBuildingShortestPath(v:Vertex, stack:Stack[Vertex]):Vertex = {
    val info = getInfoForVertex(v)
    if (info == null) return null
    stack.push(info.neighbourOnShortestPath)
    return info.neighbourOnShortestPath
  }
  
  private def getInfoForVertex(v:Vertex):ComputeInfo = {
    val opt = infos.find { x => x.v == v }
    if (!opt.isEmpty) {
      opt.get
    } else null
  }
  
  private def process(v1:Vertex, v2:Vertex) {
    while (!areAllVerticesVisited()) {
      val opt = getUnvisitedVertexWithShortestValueToSource()
      if (!opt.isEmpty) {
        val v = opt.get
    	  markAsVisited(opt.get)
        assignDistancesFromSourceToNeighbours(v)
      } else return      
    }
  }
  
  private def assignDistancesFromSourceToNeighbours(current:Vertex) {
    val valueFromSourceToCurrent = getValueFromSource(current)
    for ((neighbour, valueFromCurrentToNeighbour) <- current.edges) handleNeighbour(current, neighbour, valueFromSourceToCurrent, valueFromCurrentToNeighbour)
  }
  
  private def handleNeighbour(current:Vertex, neighbour:Vertex, valueFromSourceToCurrent:Double, valueFromCurrentToNeighbour:Double) {
    val total = valueFromSourceToCurrent + valueFromCurrentToNeighbour
    val opt = infos.find { x => x.v == neighbour }
    if (!opt.isEmpty && total < opt.get.valueFromSource) {
      val v = opt.get
      v.valueFromSource = total
      v.neighbourOnShortestPath = current
    }
  }
  
  private def getValueFromSource(v:Vertex):Double = {
    val opt = infos.find { x => x.v == v }
    if (!opt.isEmpty) opt.get.valueFromSource else 0
  }
  
  private def markAsVisited(v:Vertex) {
    val opt = infos.find { x => x.v == v }
    if (!opt.isEmpty) {
      val info = opt.get
      info.isVisited = true
    }
  }
  
  private def getUnvisitedVertexWithShortestValueToSource():Option[Vertex] = {
    val sortedInfos = infos.sortBy { x => x.valueFromSource }
    val opt = sortedInfos.find { x => !x.isVisited }
    val info = opt.getOrElse(new ComputeInfo(null))
    Some(info.v)
  }
  
  private def areAllVerticesVisited():Boolean = {
    val opt = infos.filter { x => !x.isVisited }
    opt.isEmpty
  }
  
  private def doInit(g:Graph, sourceVertex:Vertex) {
    for (v <- g.vertices) if (v == sourceVertex) infos += new ComputeInfo(v, 0) else infos += new ComputeInfo(v)
  }
}