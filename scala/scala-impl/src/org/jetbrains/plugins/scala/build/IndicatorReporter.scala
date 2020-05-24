package org.jetbrains.plugins.scala.build

import java.io.File

import com.intellij.build.FilePosition
import com.intellij.build.events.EventResult
import com.intellij.openapi.progress.ProgressIndicator
import org.jetbrains.plugins.scala.ScalaBundle

class IndicatorReporter(indicator: ProgressIndicator) extends BuildReporter {
  override def start(): Unit = {
    indicator.setText(ScalaBundle.message("report.build.running"))
  }

  override def finish(messages: BuildMessages): Unit = {
    //noinspection ScalaExtractStringToBundle
    indicator.setText2("")

    if (messages.errors.isEmpty)
      indicator.setText(ScalaBundle.message("report.build.completed"))
    else
      indicator.setText(ScalaBundle.message("report.build.failed"))
  }

  override def finishWithFailure(err: Throwable): Unit = {
    indicator.setText(ScalaBundle.message("report.failed.with.message", err.getMessage))
  }

  override def finishCanceled(): Unit = {
    indicator.setText(ScalaBundle.message("report.canceled"))
  }


  override def warning(message: String, position: Option[FilePosition]): Unit = {
    indicator.setText(ScalaBundle.message("report.warning.with.message", message))
    indicator.setText2(positionString(position))
  }

  override def error(message: String, position: Option[FilePosition]): Unit = {
    indicator.setText(ScalaBundle.message("report.error.with.message", message))
    indicator.setText2(positionString(position))
  }

  override def log(message: String): Unit = {
    indicator.setText(ScalaBundle.message("report.building"))
    indicator.setText2(message)
  }

  override def info(message: String, position: Option[FilePosition]): Unit = {
    indicator.setText(message)
    indicator.setText2(positionString(position))
  }

  private def positionString(position: Option[FilePosition]) = {
    position.fold("") { pos =>
      s"${pos.getFile} [${pos.getStartLine}:${pos.getStartColumn}]"
    }
  }

  override def startTask(eventId: BuildMessages.EventId, parent: Option[BuildMessages.EventId], message: String, time: Long): Unit = ()
  override def progressTask(eventId: BuildMessages.EventId, total: Long, progress: Long, unit: String, message: String, time: Long): Unit = ()
  override def finishTask(eventId: BuildMessages.EventId, message: String, result: EventResult, time: Long): Unit = ()
  override def clear(file: File): Unit = ()
}
