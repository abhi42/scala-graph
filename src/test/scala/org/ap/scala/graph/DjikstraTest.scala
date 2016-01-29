package org.ap.scala.graph

import org.junit.Test
import org.junit.Assert._
class DjikstraTest {
  
  @Test
  def testDjikstraGraph1 {
    val g = new Graph()
    val v1 = g.createVertex()
    val v2 = g.createVertex()
    val v3 = g.createVertex()
    val v4 = g.createVertex()
    val v5 = g.createVertex()
    val v6 = g.createVertex()
    
    v1.join(v2, 2)
    v1.join(v3, 3)
    v2.join(v5, 5)
    v3.join(v4, 1)
    v3.join(v6, 2)
    v4.join(v5, 1)
    v6.join(v5, 1)
    
    val shortestPath = Djikstra.shortestPathBetween(g, v1, v5)
    assertEquals(5.0, shortestPath.value, 0.00)
    assertEquals(List(v1, v3, v4, v5), shortestPath.path)
  }
  
  @Test
  def testDjikstraGraph2 {
    val g = new Graph()
    val v1 = g.createVertex()
    val v2 = g.createVertex()
    val v3 = g.createVertex()
    val v4 = g.createVertex()
    val v5 = g.createVertex()
        
    v1.join(v2, 7)
    v1.join(v3, 2)
    v2.join(v3, 3)
    v2.join(v5, 1)
    v3.join(v4, 4)
    v4.join(v5, 3)
    
    val shortestPath = Djikstra.shortestPathBetween(g, v1, v5)
    assertEquals(6.0, shortestPath.value, 0.00)
    assertEquals(List(v1, v3, v2, v5), shortestPath.path)
  }
}