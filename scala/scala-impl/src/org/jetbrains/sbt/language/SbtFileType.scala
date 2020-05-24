package org.jetbrains.sbt
package language

import com.intellij.openapi.util.IconLoader
import javax.swing.Icon
import org.jetbrains.plugins.scala.LanguageFileTypeBase

object SbtFileType extends LanguageFileTypeBase(SbtLanguage.INSTANCE) {
  override def getIcon: Icon =
    IconLoader.getIcon("/org/jetbrains/plugins/scala/images/sbt_file.svg")
}
