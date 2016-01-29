package org.ap.scala.graph

import org.junit.Test
import org.junit.Assert._

class GraphTest {
  
  @Test
  def testCreateVertex {
    val expected = "a name"
    
    val g = new Graph()
    val v = g.createVertex(expected)
    
    assertEquals(expected, v.name)
  }
}