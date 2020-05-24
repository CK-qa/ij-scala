package org.jetbrains.sbt
package project.template

import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.platform.{ProjectTemplate, ProjectTemplatesFactory}
import javax.swing.Icon
import org.jetbrains.plugins.scala.icons.Icons
import org.jetbrains.plugins.scala.project.template.ScalaProjectTemplatesFactory
import org.jetbrains.sbt.project.template.techhub.TechHubProjectTemplate

/**
  * User: Dmitry.Naydanov, Pavel Fatin
  * Date: 11.03.14.
  */
class SbtProjectTemplateFactory extends ProjectTemplatesFactory {
  override def getGroups: Array[String] = Array(ScalaProjectTemplatesFactory.Group)

  override def getGroupIcon(group: String): Icon = Icons.SCALA_SMALL_LOGO

  override def createTemplates(group: String, context: WizardContext): Array[ProjectTemplate] = {
    if (context.isCreatingNewProject) {
      Array(
        new SbtProjectTemplate(),
        new TechHubProjectTemplate)
    } else {
      Array.empty
    }
  }
}
