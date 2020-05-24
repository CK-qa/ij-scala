package org.jetbrains.plugins.scala
package lang
package parser

import org.jetbrains.plugins.scala.base.ScalaFileSetTestCase
import org.junit.experimental.categories.Category
import org.junit.runner.RunWith
import org.junit.runners.AllTests

/**
 * @author Nikolay.Tropin
 */
@RunWith(classOf[AllTests])
@Category(Array(classOf[PerfCycleTests]))
class FailedParserTest extends ScalaFileSetTestCase("/parser/failed") {

  override protected def shouldPass = false
}

object FailedParserTest {
  def suite = new FailedParserTest()
}