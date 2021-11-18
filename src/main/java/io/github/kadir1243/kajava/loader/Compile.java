package io.github.kadir1243.kajava.loader;

import io.github.kadir1243.kajava.Init;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;

public class Compile {
    public static final Logger LOGGER = LogManager.getLogger(Init.MODID + " Compiler System");

    public static void run(String javaDirString) {
        File[] files = new File(javaDirString).listFiles();
        if (files == null) return;
        for (File file : files) {
            Compile.compileJava(file);
        }
    }

    public static void compileJava(File file) {
        if (!file.getName().endsWith(".java")) return;
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
    // TODO: Add Other Programming Languages to compile
}
