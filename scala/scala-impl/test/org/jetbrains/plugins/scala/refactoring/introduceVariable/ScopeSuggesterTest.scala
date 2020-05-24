package org.jetbrains.plugins.scala.refactoring.introduceVariable

/**
 * Created by user 
 * on 10/15/15
 */
class ScopeSuggesterTest extends AbstractScopeSuggesterTest{
  def testOkSimple(): Unit =  doTest(Seq("class OkSimple"))

  def testOkTypeAlias(): Unit = doTest(Seq("trait B"))

  def testOkTypeAlias2(): Unit = doTest(Seq("class OkTypeAlias2", "trait B"))

  def testOkTypeParam(): Unit =  doTest(Seq("class Inner"))

  def testNokFunction(): Unit = doTest(Seq(""))
}
