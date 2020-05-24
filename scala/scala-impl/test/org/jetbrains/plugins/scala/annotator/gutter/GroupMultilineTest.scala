package org.jetbrains.plugins.scala.annotator.gutter


/**
 * Pavel.Fatin, 21.01.2010
 */

class GroupMultilineTest extends LineMarkerTestBase {
  protected override def getBasePath = super.getBasePath + "/group/multiline/"

  def testAnonymousClasses(): Unit = doTest()
  def testBlocks(): Unit = doTest()
  def testClasses(): Unit = doTest()
  def testFunctionDefinitions(): Unit = doTest()
  def testObjects(): Unit = doTest()
  def testPackageContainers(): Unit = doTest()
  def testTraits(): Unit = doTest()
  def testValues(): Unit = doTest()
  def testVariableDefinitions(): Unit = doTest()
}