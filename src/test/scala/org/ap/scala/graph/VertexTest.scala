

package org.ap.scala.graph

import org.junit.Test
import org.junit.Assert._

class VertexTest {
  
  @Test
  def testVertexConstructor = {
    val v = new Vertex("some vertex")
    assertEquals("some vertex", v.name)
  }
  
  @Test
  def testVertexConstructorUsingDefault = {
    val v1 = new Vertex()
    assertEquals("v1", v1.name)
    
    val v2 = new Vertex()
    assertEquals("v2", v2.name)
    
    val v3 = new Vertex()
    assertEquals("v3", v3.name)
  }
  
  @Test
  def testGetDistance() {
    val v1 = new Vertex()
    val v2 = new Vertex()
    
    val e = v1.join(v2, 7.5)
    
    assertEquals(7.5, e.value, VertexTest.DEFAULT_PRECISION)
    assertEquals(7.5, v1.distanceTo(v2), VertexTest.DEFAULT_PRECISION)
    assertEquals(7.5, v2.distanceTo(v1), VertexTest.DEFAULT_PRECISION)    
  }
  
  @Test
  def testGetDistanceToSelf {
    val v1 = new Vertex()
    assertEquals(0, v1.distanceTo(v1), 0.00)
  }
  
  @Test
  def testGetDistanceAPIForNonNeighbour {
    val v1 = new Vertex()
    val v2 = new Vertex()
    val v3 = new Vertex()
    
    v1.join(v2, 3)
    assertEquals(-1, v1.distanceTo(v3), VertexTest.DEFAULT_PRECISION)
  }
  
  @Test
  def testGetDistanceToNull {
    val v1 = new Vertex()
    assertEquals(-1, v1.distanceTo(null), 0.00)
  }
}

object VertexTest {
  val DEFAULT_PRECISION = 0.00000
}