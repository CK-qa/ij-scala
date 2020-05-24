package org.jetbrains.jps.incremental.scala.local.worksheet

import java.io.{File, PrintWriter, Flushable}

import org.jetbrains.jps.incremental.scala.local.worksheet.ILoopWrapper
import org.jetbrains.jps.incremental.scala.local.worksheet.ILoopWrapper213_0Impl.DummyConfig

import scala.reflect.classTag
import scala.reflect.internal.util.Position
import scala.tools.nsc.Settings
import scala.tools.nsc.interpreter.NamedParam.Typed
import scala.tools.nsc.interpreter.StdReplTags.tagOfIMain
import scala.tools.nsc.interpreter.shell.{ILoop, ReplReporterImpl, ShellConfig}
import scala.tools.nsc.interpreter.{IMain, Results}

import scala.jdk.CollectionConverters._

/**
 * ATTENTION: when editing ensure to increase the version in ILoopWrapperFactoryHandler
 */
class ILoopWrapper213_0Impl(
  myOut: PrintWriter,
  wrapperReporter: ILoopWrapperReporter,
  projectFullCp: java.util.List[String],
  scalaOptions: java.util.List[String]
) extends ILoop(new DummyConfig, out = myOut)
  with ILoopWrapper {

  override def getOutput: Flushable = myOut

  override def init(): Unit = {
    val mySettings = new Settings
    mySettings.processArguments(scalaOptions.asScala.toList, processAll = true)
    mySettings.classpath.append(projectFullCp.asScala.mkString(File.pathSeparator))
    // do not use java class path because it contains scala library jars with version
    // different from one that is used during compilation (it is passed from the plugin classpath)
    mySettings.usejavacp.tryToSetFromPropertyValue(false.toString)

    createInterpreter(mySettings)

    val itp = intp.asInstanceOf[IMain]
    itp.initializeCompiler()
    itp.quietBind(new Typed[IMain]("$intp", itp)(tagOfIMain, classTag[IMain]))
  }

  override def createInterpreter(interpreterSettings: Settings): Unit = {
    val reporter = new ReplReporterImpl(new DummyConfig, interpreterSettings, out) {
      override def print(pos: Position, msg: String, severity: Severity): Unit =
        wrapperReporter.report(severity.toString, pos.line, pos.column, pos.lineContent, msg)
    }
    intp = new IMain(interpreterSettings, None, interpreterSettings, reporter)
  }

  override def reset(): Unit = {
    intp.reset()
  }

  override def shutdown(): Unit = {
    closeInterpreter()
  }

  override def processChunk(code: String): Boolean =
    intp.interpret(code) match {
      case Results.Success => true
      case _ => false
    }
}

object ILoopWrapper213_0Impl {

  class DummyConfig extends ShellConfig {
    override def filesToPaste: List[String] = List.empty
    override def filesToLoad: List[String] = List.empty
    override def batchText: String = ""
    override def batchMode: Boolean = false
    override def doCompletion: Boolean = false
    override def haveInteractiveConsole: Boolean = false
  }
}