package org.jetbrains.plugins.scala
package lang
package parser
package parsing
package top

import org.jetbrains.plugins.scala.lang.parser.parsing.base.AccessModifier
import org.jetbrains.plugins.scala.lang.parser.parsing.builder.ScalaPsiBuilder
import org.jetbrains.plugins.scala.lang.parser.parsing.expressions.Annotations

/**
 * [[ConstrMods]] ::= [[Annotations]] [ [[AccessModifier]] ]
 *
 * @author adkozlov
 */
object ConstrMods extends ParsingRule {

  override def apply()(implicit builder: ScalaPsiBuilder): Boolean = {
    Annotations.parseOnTheSameLine()

    val modifiersMarker = builder.mark()
    if (!builder.newlineBeforeCurrentToken) {
      AccessModifier.parse(builder)
    }
    modifiersMarker.done(ScalaElementType.MODIFIERS)

    true
  }
}
