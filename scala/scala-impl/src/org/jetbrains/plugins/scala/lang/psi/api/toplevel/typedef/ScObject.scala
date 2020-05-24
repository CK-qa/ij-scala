package org.jetbrains.plugins.scala
package lang
package psi
package api
package toplevel
package typedef

import com.intellij.psi.PsiClass
import org.jetbrains.plugins.scala.lang.psi.api.statements.ScDeclaredElementsHolder

/**
* @author Alexander Podkhalyuzin
* Date: 20.02.2008
*/
trait ScObject extends ScTypeDefinition
  with ScTypedDefinition
  with ScMember
  with ScDeclaredElementsHolder {

  //Is this object generated as case class companion module
  private var flag = false

  def isSyntheticObject: Boolean = flag

  //noinspection AccessorLikeMethodIsUnit
  def isSyntheticObject_=(flag: Boolean): Unit = {
    this.flag = flag
  }

  override def declaredElements: Seq[ScObject] = Seq(this)

  def hasPackageKeyword: Boolean

  def fakeCompanionClass: Option[PsiClass]

  def fakeCompanionClassOrCompanionClass: PsiClass

  /** Is this object accessible from a stable path from the root package? */
  def isStatic: Boolean = containingClass match {
    case obj: ScObject => obj.isStatic
    case null => true
    case _ => false
  }
}