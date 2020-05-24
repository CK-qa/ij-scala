package org.jetbrains.plugins.scala
package lang
package psi
package impl
package base
package types

import com.intellij.lang.ASTNode
import org.jetbrains.plugins.scala.lang.psi.api.base.{ScLiteral, types}
import org.jetbrains.plugins.scala.lang.psi.types.{ScLiteralType, result}

final class ScLiteralTypeElementImpl(node: ASTNode) extends ScalaPsiElementImpl(node)
  with types.ScLiteralTypeElement {

  override protected def innerType: result.TypeResult =
    getLiteral.getNonValueType().map {
      case literalType: ScLiteralType => literalType.blockWiden
      case resultType => resultType
    }

  override def getLiteral: ScLiteral = getFirstChild.asInstanceOf[ScLiteral]
}
