package org.jetbrains.plugins.scala
package lang
package psi
package impl
package base
package literals

import com.intellij.lang.ASTNode
import com.intellij.openapi.project.Project
import org.jetbrains.plugins.scala.lang.psi.api.base.{ScLiteral, literals}
import org.jetbrains.plugins.scala.lang.psi.types.ScType

final class ScSymbolLiteralImpl(node: ASTNode,
                                override val toString: String)
  extends QuotedLiteralImplBase(node, toString)
    with literals.ScSymbolLiteral {

  override protected def startQuote: String = QuotedLiteralImplBase.CharQuote

  override protected def endQuote: String = ""

  override protected def wrappedValue(value: Symbol): ScLiteral.Value[Symbol] =
    ScSymbolLiteralImpl.Value(value)

  override protected def toValue(name: String): Symbol = Symbol(name)
}

object ScSymbolLiteralImpl {

  final case class Value(override val value: Symbol) extends ScLiteral.Value(value) {

    override def wideType(implicit project: Project): ScType = cachedClass("scala.Symbol")
  }
}