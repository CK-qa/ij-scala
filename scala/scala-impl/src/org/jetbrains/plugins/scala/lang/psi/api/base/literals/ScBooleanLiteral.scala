package org.jetbrains.plugins.scala
package lang
package psi
package api
package base
package literals

trait ScBooleanLiteral extends ScLiteral {
  override protected type V = java.lang.Boolean
}

object ScBooleanLiteral {

  def unapply(literal: ScBooleanLiteral): Some[Boolean] =
    Some(literal.getValue)
}