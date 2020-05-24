package org.jetbrains.plugins.scala.util

import java.io.File

import com.intellij.openapi.application.PathManager

object ScalaPluginUtils {

  private val ScalaPluginHomeFolders = Set(
    ".ScalaPluginIU",
    ".ScalaPluginIC"
  )

  /** analogue of [[com.intellij.ide.plugins.PluginManagerCore#isRunningFromSources]] */
  val isRunningFromSources: Boolean = try {
    val home = new File(PathManager.getHomePath)
    val parent2 = home.getParentFile.getParentFile
    parent2.isDirectory && ScalaPluginHomeFolders.contains(parent2.getName)
  } catch {
    case _: Throwable => false
  }
}
