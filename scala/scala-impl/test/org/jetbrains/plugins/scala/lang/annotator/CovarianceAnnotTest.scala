package org.jetbrains.plugins.scala.lang.annotator

import org.jetbrains.plugins.scala.base.ScalaLightCodeInsightFixtureTestAdapter

/**
  * Created by Anton Yalyshev on 16/05/16.
  */
class CovarianceAnnotTest extends ScalaLightCodeInsightFixtureTestAdapter {
  def testSCL10263(): Unit = {
    checkTextHasNoErrors(
      """
        |trait Foo[+A] {
        |  trait Bar
        |}
        |
        |trait Baz[+A] {
        |  val x: Foo[A]#Bar
        |}
      """.stripMargin
    )
  }

  def testSCL14032(): Unit = {
    val text =
      """
        |class Queue[+T] private (private[this] var leading: List[T],
        |                         private[this] var trailing: List[T])
      """.stripMargin
    checkTextHasNoErrors(text)
  }
}