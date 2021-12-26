package io.github.kadir1243.kajava.loader;

import io.github.kadir1243.kajava.Init;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;

public class Compile {
    public static final Logger LOGGER = LogManager.getLogger(Init.MODID + " Compiler System");

    public static void run(String javaDirString) {
        compileJavaFiles(javaDirString, Init.getConfig().extraPackages.toArray(new String[0]));
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

    public static void compileJavaFiles(String javaDirString, @Nullable String[] extraPackageDirs) {
        File[] javaFiles = new File(javaDirString).listFiles();
        if (javaFiles == null) return;
        for (File file : javaFiles) {
            compileJava(file);
        }
        if (extraPackageDirs != null) {
            for (String packages : extraPackageDirs) {
                if (packages != null) {
                    compileJava(new File(packages));
                }
            }
        }
    }
    // TODO: Add Other Programming Languages to compile
}
