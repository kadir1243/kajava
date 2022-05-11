package io.github.kadir1243.kajava.loader;

import io.github.kadir1243.kajava.Init;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;

public class Compile {
    public static final Logger LOGGER = LoggerFactory.getLogger(Init.MODID + " Compiler System");

    public static void run(String javaDirString) {
        compileJavaFiles(javaDirString);
    }

    public static void compileJava(File file) {
        if (file == null || !file.exists()) return;
        if (!Init.getConfig().javaCompile || !file.getName().endsWith(".java")) return;
        try {
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(file);
            compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();
            fileManager.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Deprecated(forRemoval = true, since = "0.0.7")
    public static void compileJavaFiles(String javaDirString, @SuppressWarnings("unused") @Deprecated(forRemoval = true) @Nullable String[] extraPackageDirs) {
        compileJavaFiles(javaDirString);
    }

    public static void compileJavaFiles(String javaDirString) {
        File[] javaFiles = new File(javaDirString).listFiles();
        if (javaFiles == null) return;
        for (File file : javaFiles) {
            if (file.isDirectory()) compileJavaFiles(javaDirString);
            else compileJava(file);
        }
    }
// TODO: Add Other Programming Languages to compile
}
