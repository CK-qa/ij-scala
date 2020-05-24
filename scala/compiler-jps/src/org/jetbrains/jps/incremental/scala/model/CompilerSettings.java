package org.jetbrains.jps.incremental.scala.model;

import org.jetbrains.plugins.scala.compiler.CompileOrder;
import org.jetbrains.plugins.scala.compiler.data.SbtIncrementalOptions;

/**
 * @author Pavel Fatin
 */
public interface CompilerSettings {
  CompileOrder getCompileOrder();

  SbtIncrementalOptions getSbtIncrementalOptions();

  String[] getCompilerOptions();
}
