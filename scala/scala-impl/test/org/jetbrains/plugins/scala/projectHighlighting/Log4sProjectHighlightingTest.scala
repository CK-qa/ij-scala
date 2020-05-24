package org.jetbrains.plugins.scala.projectHighlighting

import com.intellij.openapi.util.TextRange
import org.jetbrains.plugins.scala.HighlightingTests
import org.junit.experimental.categories.Category

@Category(Array(classOf[HighlightingTests]))
class Log4sProjectHighlightingTest extends GithubSbtAllProjectHighlightingTest {
  override def githubUsername = "Log4s"

  override def githubRepoName = "log4s"

  override def revision = "8439aef843da2c9f489d1dff4cf62df6135fb9d8"

  override def filesWithProblems: Map[String, Set[TextRange]] = Map(
    "src/test/scala/org/log4s/MDCSpec.scala" -> Set((356,374), (477,495), (904,922), (1049,1067), (1121,1147), (1173,1204), (1355,1373)),
  )
}
