package org.jetbrains.plugins.scala.testingSupport.specs2

/**
 * @author Roman.Shein
 * @since 27.01.2015.
 */
abstract class Specs2DuplicateConfigTest extends Specs2TestCase {
  addSourceFile("DuplicateConfigTest.scala",
    """
      |import org.specs2.mutable.Specification
      |
      |class DuplicateTest extends Specification {
      |  "The 'SpecificationTest'" should {
      |    "create only one config" in {
      |      success
      |    }
      |
      |    "have something fancy in code" in {
      |      success
      |    }
      |  }
      |}
    """.stripMargin
  )
  def testDuplicateConfig(): Unit = {
    runDuplicateConfigTest(5, 10, "DuplicateConfigTest.scala",
      checkConfigAndSettings(_, "DuplicateConfigTest", "create only one config")
    )
  }
}
