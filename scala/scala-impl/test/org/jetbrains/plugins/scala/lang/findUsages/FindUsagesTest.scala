package org.jetbrains.plugins.scala.lang.findUsages

import com.intellij.find.findUsages.FindUsagesOptions
import com.intellij.openapi.util.TextRange
import com.intellij.openapi.util.text.StringUtil
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.usageView.UsageInfo
import com.intellij.util.Processor
import org.jetbrains.plugins.scala.base.ScalaFixtureTestCase
import org.jetbrains.plugins.scala.findUsages.factory.{ScalaFindUsagesHandler, ScalaFindUsagesHandlerFactory, ScalaTypeDefinitionFindUsagesOptions}
import org.jetbrains.plugins.scala.lang.psi.api.toplevel.ScNamedElement
import org.jetbrains.plugins.scala.util.Markers

/**
  * Nikolay.Tropin
  * 21-Aug-17
  */
class FindUsagesTest extends ScalaFixtureTestCase with Markers {
  private def classWithMembersOptions: FindUsagesOptions = {
    val options = new ScalaTypeDefinitionFindUsagesOptions(getProject)
    options.isMembersUsages = true
    options
  }

  private def defaultOptions = new ScalaTypeDefinitionFindUsagesOptions(getProject)

  private def doTest(fileText: String, options: FindUsagesOptions = defaultOptions): Unit = {
    val (source, expectedUsageRanges) = extractMarkers(StringUtil.convertLineSeparators(fileText))
    myFixture.configureByText("dummy.scala", source)
    val elem = myFixture.getElementAtCaret
    val named = PsiTreeUtil.getParentOfType(elem, classOf[ScNamedElement], false)
    val handler = new ScalaFindUsagesHandler(named, ScalaFindUsagesHandlerFactory.getInstance(getProject))

    val foundUsages = {
      val resultBuilder = Set.newBuilder[TextRange]
      val dummyProcessor: Processor[UsageInfo] = (usage) => {
        resultBuilder += usage.getElement.getTextRange
        true
      }
      handler.processElementUsages(named, dummyProcessor, options)
      resultBuilder.result()
    }

    val expectedButNotFound = expectedUsageRanges.filterNot(foundUsages.contains)
    val foundButNotExpected = foundUsages.filterNot(expectedUsageRanges.contains)

    assert(expectedButNotFound.isEmpty, s"Didn't find ${expectedButNotFound.mkString(", ")} but found ${foundButNotExpected.mkString(", ")}")
    assert(foundButNotExpected.isEmpty, s"Found but didn't expect ${foundButNotExpected.mkString(", ")}")
  }

  def testFindObjectWithMembers(): Unit = {
    doTest(
      s"""object <caret>Test {
        |  def foo() = ???
        |
        |  ${start(0)}${start(1)}Test${end(1)}.foo${end(0)}
        |}
        |""".stripMargin,
      classWithMembersOptions)
  }

  def testFindValOverriders(): Unit = {
    doTest(
      s"""
        |trait FindMyMembers {
        |  val findMyVal<caret>: Int
        |  def methodInTrait(): Unit = {
        |    println(${start(0)}findMyVal${end(0)})
        |  }
        |}
        |
        |class FindMyMembersImpl extends FindMyMembers {
        |  override val findMyVal: Int = 1
        |
        |  def methodInImpl(): Unit = {
        |    println(${start(1)}findMyVal${end(1)})
        |  }
        |}
      """.stripMargin)
  }

  def testFindDefOverriders(): Unit = {
    doTest(
      s"""
        |trait FindMyMembers {
        |  def findMyDef<caret>: Int
        |  def methodInTrait(): Unit = {
        |    println(${start(0)}findMyDef${end(0)})
        |  }
        |}
        |
        |class FindMyMembersImpl extends FindMyMembers {
        |  override def findMyDef: Int = 1
        |
        |  def methodInImpl(): Unit = {
        |    println(${start(1)}findMyDef${end(1)})
        |  }
        |}
        |
        |class FindMyMembersImpl2 extends FindMyMembers {
        |  override val findMyDef: Int = 2
        |
        |  def methodInImpl2(): Unit = {
        |    println(${start(2)}findMyDef${end(2)})
        |  }
        |}
        |
        |class FindMyMembersImpl3 extends FindMyMembers {
        |  override var findMyDef: Int = 2
        |
        |  def methodInImpl2(): Unit = {
        |    ${start(3)}findMyDef${end(3)} = 3
        |    println(${start(4)}findMyDef${end(4)})
        |  }
        |}
      """.stripMargin)
  }

}
