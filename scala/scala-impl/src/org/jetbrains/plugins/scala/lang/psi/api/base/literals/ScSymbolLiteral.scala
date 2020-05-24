package org.jetbrains.plugins.scala
package lang
package psi
package api
package base
package literals

trait ScSymbolLiteral extends ScLiteral {
  override protected type V = Symbol
}

object ScSymbolLiteral {

  def unapply(literal: ScSymbolLiteral): Option[Symbol] =
    Option(literal.getValue)
}
