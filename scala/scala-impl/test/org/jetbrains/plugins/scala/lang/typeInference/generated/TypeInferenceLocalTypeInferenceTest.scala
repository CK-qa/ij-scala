package org.jetbrains.plugins.scala.lang.typeInference
package generated

class TypeInferenceLocalTypeInferenceTest extends TypeInferenceTestBase {
  //This class was generated by build script, please don't change this
  override def folderPath: String = super.folderPath + "localTypeInference/"

  def testAbstractExpectedType(): Unit = {doTest()}

  def testAnyValUpperLowerBound(): Unit = {doTest()}

  def testArrayCreation(): Unit = {doTest()}

  def testAsInstanceOfOmittedGenerics(): Unit = {doTest()}

  def testCompoundType(): Unit = {doTest()}

  def testEmptyArray(): Unit = {doTest()}

  def testExistentialInference(): Unit = {doTest()}

  def testForIndexedSeq(): Unit = {doTest()}

  def testFromImplicitParam(): Unit = {doTest()}

  def testFromImplicitParameter(): Unit = {doTest()}

  def testInfixMap(): Unit = {doTest()}

  def testIntArrayCreation(): Unit = {doTest()}

  def testListUnion(): Unit = {doTest()}

  def testMap(): Unit = {doTest()}

  def testMapAsFunction(): Unit = {doTest()}

  def testMostSpecificRuleImplicitParameters(): Unit = {doTest()}

  def testNoSOE(): Unit = {doTest()}

  def testOmittedGenericsWithOurClass(): Unit = {doTest()}

  def testSCL1547(): Unit = {doTest()}

  def testSCL1644(): Unit = {doTest()}

  def testSCL1671(): Unit = {doTest()}

  def testSCL1687(): Unit = {doTest()}

  def testSCL1771(): Unit = {doTest()}

  def testSCL1771First(): Unit = {doTest()}

  def testSCL1772(): Unit = {doTest()}

  def testSCL1824(): Unit = {doTest()}

  def testSCL3422(): Unit = {doTest()}

  def testPatternExistential(): Unit = {doTest()}

  def testRawTypes(): Unit = {doTest()}

  def testSameNameGeneric(): Unit = {doTest()}

  def testSecondClause(): Unit = {doTest()}

  def testSelfTypeExpected(): Unit = {doTest()}

  def testSimpleTypeAlias(): Unit = {doTest()}

  def testSmartArrayMap(): Unit = {doTest()}

  def testSmartArrayMap2(): Unit = {doTest()}

  def testSome(): Unit = {doTest()}

  def testSynchronizedWithGenerics(): Unit = {doTest()}

  def testToArray(): Unit = {doTest()}

  def testTypedPatternInference(): Unit = {doTest()}

  def testZipArrays(): Unit = {doTest()}

  def testZipWithIndex(): Unit = {doTest()}

  def testsyncronized(): Unit = {doTest()}

  def testSCL2507_1(): Unit = {doTest()}

  def testSCL2507_2(): Unit = {doTest()}

  def testSCL2507_3(): Unit = {doTest()}

  def testSCL2507_4(): Unit = {doTest()}

  def testSCL2507_5(): Unit = {doTest()}

  def testSCL12598(): Unit = doTest()

  def testSCL5809(): Unit = doTest {
    """
      |object SCL5809 {
      |
      |  trait Functor[F[_]] {
      |    def map[A, B](fa: F[A])(f: A => B): F[B]
      |  }
      |
      |  trait Applicative[F[_]] extends Functor[F] {
      |    def map2[A, B, C](fa: F[A], fb: F[B])(f: (A, B) => C): F[C]
      |
      |    def apply[A, B](fab: F[A => B])(fa: F[A]): F[B]
      |
      |    def unit[A](a: A): F[A]
      |
      |    // Excercise 1
      |    def map2ApplyUnit[A, B, C](fa: F[A], fb: F[B])(f: (A, B) => C): F[C] = {
      |      apply[B, C](apply[A, B => C](unit(f.curried))(fa))(fb)
      |    }
      |
      |    def applyMap2Unit[A, B](fab: F[A => B])(fa: F[A]): F[B] = {
      |      map2(fab, fa)((f, a) => f(a))
      |    }
      |
      |    def map[A, B](fa: F[A])(f: A => B): F[B] = {
      |      apply(/*start*/unit(f)/*end*/)(fa)
      |    }
      |  }
      |
      |  object Applicative {
      |  }
      |
      |}
      |//F[A => B]
    """.stripMargin.trim
  }

}