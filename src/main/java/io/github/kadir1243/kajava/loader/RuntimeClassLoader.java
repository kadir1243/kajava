package io.github.kadir1243.kajava.loader;

import groovy.lang.GroovyClassLoader;
import io.github.kadir1243.kajava.Init;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Objects;

public class RuntimeClassLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(RuntimeClassLoader.class);
    public static void run(String javaDirString,String groovyDirString) {
        try {
            File javaFile = new File(javaDirString);
            File groovyFile = new File(groovyDirString);
            Compile.run(javaDirString);
            if (javaFile.listFiles() != null) {
                for (File file : Objects.requireNonNull(javaFile.listFiles())) {
                    URL url = javaFile.toURI().toURL();
                    URL[] urls = new URL[]{url};
                    ClassLoader classLoader = new URLClassLoader(urls);
                    loadJavaFile(file, classLoader);
                }
            }
            if (groovyFile.listFiles() != null){
                for (File file : Objects.requireNonNull(groovyFile.listFiles())) {
                    URL url = javaFile.toURI().toURL();
                    URL[] urls = new URL[]{url};
                    ClassLoader classLoader = new URLClassLoader(urls);
                    loadGroovyFile(file, classLoader);
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static void loadGroovyFile(File file,ClassLoader classLoader) {
        if (!file.getName().endsWith(".groovy") || !Init.getConfig().groovyRuns) return;
        try (GroovyClassLoader groovyClassLoader = new GroovyClassLoader(classLoader)) {
            Class<?> loadedClass = groovyClassLoader.parseClass(file);
            for (String s : Init.getConfig().runnableClasses) {
                if ((loadedClass.getPackageName() + (loadedClass.getPackageName().equals("") ? "" : ".") + loadedClass.getName()).equals(s)) {
                    runMethods(loadedClass);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadJavaFile(File file,ClassLoader classLoader) {
        if (!file.getName().endsWith(".class") || !Init.getConfig().javaRuns) return;
        try {
            Class<?> loadedClass = classLoader.loadClass(file.getName().replaceAll(".class",""));
            for (String s : Init.getConfig().runnableClasses) {
                if ((loadedClass.getPackageName() + (loadedClass.getPackageName().equals("") ? "" : ".") + loadedClass.getName()).equals(s)) {
                    Class<?> runnableClass = Class.forName(loadedClass.getName(), true, classLoader);
                    runMethods(runnableClass);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
    public static void runMethods(Class<?> loadedClass) throws ReflectiveOperationException {
        Method run = loadedClass.getMethod("run");
        Method runClient = loadedClass.getMethod("runClient");
        Method runServer = loadedClass.getMethod("runServer");
        Method condition = loadedClass.getMethod("condition");
        Method serverCondition = loadedClass.getMethod("serverCondition");
        Method clientCondition = loadedClass.getMethod("clientCondition");
        @SuppressWarnings("deprecation")
        Object newSystem = loadedClass.newInstance();
        if (!condition.isDefault() || !serverCondition.isDefault() || !clientCondition.isDefault()) {
            LOGGER.warn("Condition methods are deprecated and planned to remove");
        }
        if ((boolean) condition.invoke(newSystem)) {
            run.invoke(newSystem);
        }
        if ((boolean) clientCondition.invoke(newSystem) && Init.isClient) {
            runClient.invoke(newSystem);
        }
        if ((boolean) serverCondition.invoke(newSystem) && !Init.isClient) {
            runServer.invoke(newSystem);
        }
    }
}
