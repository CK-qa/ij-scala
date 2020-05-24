package org.jetbrains.plugins.scala.lang.typeConformance
package generated

class TypeConformanceFunctionTest extends TypeConformanceTestBase {
  //This class was generated by build script, please don't change this
  override def folderPath: String = super.folderPath + "function/"

  def testToAnyRef(): Unit = {doTest()}

  def testToClass(): Unit = {doTest()}

  def testScl7462(): Unit = {
    doTest(
      """import scala.collection.GenTraversableOnce
        |
        |def f(curry: String)(i: String): Option[String] = Some(i)
        |
        |val x: (String) => GenTraversableOnce[String] = f("curry")
        |//True""".stripMargin)
  }

}