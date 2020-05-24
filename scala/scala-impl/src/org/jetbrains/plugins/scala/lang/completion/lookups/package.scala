package org.jetbrains.plugins.scala
package lang
package completion

import com.intellij.codeInsight.lookup.LookupElementPresentation
import com.intellij.codeInsight.lookup.impl.JavaElementLookupRenderer.isDeprecated
import com.intellij.psi.PsiNamedElement
import com.intellij.util.IconUtil.getEmptyIcon

package object lookups {

  // TODO extract as a customizable LookupElementRenderer
  private[completion] implicit class PresentationExt(private val presentation: LookupElementPresentation) extends AnyVal {

    def setStrikeout(element: PsiNamedElement): Unit =
      presentation.setStrikeout {
        isReal && isDeprecated(element)
      }

    def setIcon(element: PsiNamedElement): Unit = presentation.setIcon {
      if (isReal) element.getIcon(0)
      else getEmptyIcon(false)
    }

    def appendGrayedTailText(text: String): Unit =
      if (text != null && text.nonEmpty) {
        presentation.appendTailText(text, true)
      }

    private def isReal = presentation.isReal
  }
}
