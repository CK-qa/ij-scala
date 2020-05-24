package org.jetbrains.plugins.scala
package lang
package psi
package api
package base
package literals

import com.intellij.openapi.util.Key
import org.jetbrains.plugins.scala.lang.psi.types.ScType

trait ScNullLiteral extends ScLiteral {
  override protected type V = Null
}

object ScNullLiteral {

  private[this] val TypeKey = Key.create[ScType]("scala.type.without.implicits")

  def unapply(literal: ScNullLiteral): Option[ScType] =
    Option(literal.getCopyableUserData(TypeKey))

  def update(literal: ScNullLiteral,
             `type`: ScType): Unit = {
    literal.putCopyableUserData(TypeKey, `type`)
  }
}
