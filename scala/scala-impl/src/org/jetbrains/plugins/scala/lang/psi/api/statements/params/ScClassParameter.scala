package org.jetbrains.plugins.scala
package lang
package psi
package api
package statements
package params

import com.intellij.lang.jvm.JvmElementVisitor
import org.jetbrains.plugins.scala.lang.psi.api.toplevel.typedef.ScMember

/**
* @author Alexander Podkhalyuzin
* Date: 22.02.2008
*/
trait ScClassParameter extends ScParameter with ScMember.WithBaseIconProvider {
  def isVal: Boolean

  def isVar: Boolean

  def isPrivateThis: Boolean

  def isClassMember: Boolean = isVal || isVar || isCaseClassVal

  /** Is the parameter automatically a val, due to it's position in a case class parameter list */
  def isCaseClassVal: Boolean

  override def accept[T](visitor: JvmElementVisitor[T]): T = super[WithBaseIconProvider].accept(visitor)
}