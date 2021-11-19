package io.github.kadir1243.kajava.loader;

import io.github.kadir1243.kajava.Init;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Objects;

@SuppressWarnings("deprecation")
public class ClassLoadRuntime {
    public static void run(String javaDirString) {
        File javaFile = new File(javaDirString);
        for (File file : Objects.requireNonNull(javaFile.listFiles())) {
            try {
                Compile.run(javaDirString);
                URL url = javaFile.toURI().toURL();
                URL[] urls = new URL[]{url};
                ClassLoader classLoader = new URLClassLoader(urls);
                if (file.getName().endsWith(".class")) {
                    if (!Init.getConfig().javaRuns) return;
                    Class<?> cls = classLoader.loadClass(file.getName().replaceAll(".class",""));
                    for (String s : Init.getConfig().runnableClasses) {
                        if ((cls.getPackageName() + (cls.getPackageName().equals("") ? "" : ".") + cls.getName()).equals(s)) {
                            Class<?> loadedClass = Class.forName(cls.getName(),true,classLoader);
                            Method run = loadedClass.getMethod("run");
                            Method runClient = loadedClass.getMethod("runClient");
                            Method runServer = loadedClass.getMethod("runServer");
                            Method condition = loadedClass.getMethod("condition");
                            Method serverCondition = loadedClass.getMethod("serverCondition");
                            Method clientCondition = loadedClass.getMethod("clientCondition");
                            Object newSystem = loadedClass.newInstance();
                            if ((boolean)condition.invoke(newSystem)) run.invoke(newSystem);
                            if ((boolean)clientCondition.invoke(newSystem) && (Init.isClient)) runClient.invoke(newSystem);
                            if ((boolean)serverCondition.invoke(newSystem) && (!Init.isClient)) runServer.invoke(newSystem);
                        }
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
}
