package org.jetbrains.plugins.scala.annotator

import org.jetbrains.plugins.scala.javaHighlighting.JavaHighlightingTestBase

/**
  * @author Anton Yalyshev
  * @since 06/09/18
  */
class JavaHighlightingTest extends JavaHighlightingTestBase() {

  def testSCL12136(): Unit = {
    val scala =
      """
        |import OfferRepository._
        |
        |class OfferService {
        |  val repository = new OfferRepository
        |
        |  def getOffers(rule: Rule[_]) = repository.findOffersByRule(rule)
        |}
      """.stripMargin

    val java =
      """
        |import java.io.Serializable;
        |import java.util.HashSet;
        |import java.util.List;
        |import java.util.Set;
        |import java.util.stream.Collectors;
        |
        |public class OfferRepository
        |{
        |  public List<Offer> findOffersByRule(final Rule<?> rule)
        |  {
        |    return Offer.getAllOffers()
        |                .stream()
        |                .filter(offer -> offer.getRules().contains(rule))
        |                .collect(Collectors.toList());
        |  }
        |
        |  public static class Offer
        |  {
        |    private Set<Rule> rules = new HashSet<Rule>();
        |
        |    public Set<Rule> getRules()
        |    {
        |      return rules;
        |    }
        |
        |    public static Set<Offer> getAllOffers()
        |    {
        |      return new HashSet<Offer>();
        |    }
        |  }
        |
        |  public abstract static class Rule<T extends Serializable>
        |  {
        |  }
        |}
      """.stripMargin

    assertNothing(errorsFromScalaCode(scala, java))
  }
}
