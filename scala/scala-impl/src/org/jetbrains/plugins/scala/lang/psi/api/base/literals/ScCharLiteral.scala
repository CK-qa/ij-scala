package org.jetbrains.plugins.scala
package lang
package psi
package api
package base
package literals

trait ScCharLiteral extends ScLiteral {
  override protected type V = Character
}

object ScCharLiteral {

  def unapply(literal: ScCharLiteral): Option[Char] =
    Option(literal.getValue).map(_.charValue) // DO NOT REMOVE MAPPING
}
