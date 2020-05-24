package org.jetbrains.plugins.scala
package codeInsight
package daemon

import java.util.function.Predicate

import com.intellij.codeInsight.daemon.impl.{HighlightInfo, HighlightInfoType}
import com.intellij.openapi.util.text.StringUtil.convertLineSeparators
import org.junit.Assert.assertTrue

abstract class ScalaAnnotatorHighlighterVisitorTestBase extends base.ScalaLightCodeInsightFixtureTestAdapter {

  /**
   * The appropriate [[LanguageFileTypeBase]] is supposed to be detected automatically by the file extension.
   *
   * @param fileText the text to load into the in-memory editor.
   * @param fileName the name of the file (which is used to determine the file type based on the registered filename patterns).
   */
  protected final def doTest(fileText: String,
                             fileName: String = "Foo.scala")
                            (predicate: Predicate[HighlightInfo] = Function.const(true)(_)): Unit = {
    myFixture.configureByText(fileName, convertLineSeparators(fileText))
    val actual = myFixture.doHighlighting().stream()
    assertTrue(actual.anyMatch(predicate))
  }
}

class ScalaAnnotatorHighlighterVisitorTest extends ScalaAnnotatorHighlighterVisitorTestBase {

  def testScalaFile(): Unit = doTest(
    "trait Foo() {}"
  ) {
    _.`type` == HighlightInfoType.ERROR
  }

  def testSbtFile(): Unit = doTest(
    fileText =
      s"""name := "test"
         |
         |version := "0.1"
         |
         |scalaVersion := "${Scala_2_13.minor}"
         |""".stripMargin,
    fileName = "build.sbt"
  )()

  def testWorksheetFile(): Unit = doTest(
    "val foo = 42",
    "worksheet.sc"
  )()
}

class Scala3AnnotatorHighlighterVisitorTest extends ScalaAnnotatorHighlighterVisitorTestBase {

  override protected def supportedIn(version: ScalaVersion) = version >= Scala_3_0

  def testScalaFile(): Unit = doTest(
    "trait Foo() {}"
  )()
}