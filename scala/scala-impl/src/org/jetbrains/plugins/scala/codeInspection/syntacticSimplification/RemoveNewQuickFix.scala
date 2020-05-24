package org.jetbrains.plugins.scala
package codeInspection
package syntacticSimplification

import com.intellij.openapi.project.Project
import org.jetbrains.plugins.scala.lang.psi.api.expr.ScNewTemplateDefinition
import org.jetbrains.plugins.scala.lang.psi.impl.ScalaPsiElementFactory.createExpressionFromText

/**
  * mattfowler
  * 5/7/16.
  */
final class RemoveNewQuickFix(param: ScNewTemplateDefinition) extends AbstractFixOnPsiElement(ScalaBundle.message("new.on.case.class.instantiation.redundant"), param) {

  override protected def doApplyFix(p: ScNewTemplateDefinition)
                                   (implicit project: Project): Unit = {
    p.targetToken.delete()
    p.replaceExpression(createExpressionFromText(p.getText), removeParenthesis = false)
  }
}
