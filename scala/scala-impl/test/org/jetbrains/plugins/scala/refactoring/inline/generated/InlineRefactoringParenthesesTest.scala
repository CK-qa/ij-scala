package org.jetbrains.plugins.scala
package refactoring.inline
package generated

class InlineRefactoringParenthesesTest extends InlineRefactoringTestBase {
  //This class was generated by build script, please don't change this
  override def folderPath: String = super.folderPath + "parentheses/"

  def testIfStmt(): Unit = doTest()

  def testInfix(): Unit = doTest()

  def testInfixAssoc(): Unit = doTest()

  def testInfixMult(): Unit = doTest()
}