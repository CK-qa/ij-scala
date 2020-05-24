package org.jetbrains.plugins.scala.uast;

import com.intellij.DynamicBundle;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

public class ScalaUastBundle extends DynamicBundle {
    @NonNls
    private static final String BUNDLE = "messages.ScalaUastBundle";

    private static final ScalaUastBundle INSTANCE = new ScalaUastBundle();

    private ScalaUastBundle() {
        super(BUNDLE);
    }

    @Nls
    public static String message(@NotNull @PropertyKey(resourceBundle = BUNDLE) String key, @NotNull Object... params) {
        return INSTANCE.getMessage(key, params);
    }
}