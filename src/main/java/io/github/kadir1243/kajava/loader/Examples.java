package io.github.kadir1243.kajava.loader;

import io.github.kadir1243.kajava.Init;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Examples {
    private static final Logger LOGGER = LoggerFactory.getLogger(Init.MODID + " Example Creater");
    public static void init() {
        try {
            if (Init.getConfig().createExample){
                createJavaExample();
                createGroovyExample();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void createJavaExample() throws IOException {
        File javaExample = new File(FabricLoader.getInstance().getConfigDir().toString() + "\\" + Init.MODID + "\\source\\java\\Example.java");
        if (javaExample.createNewFile()) {
            FileWriter writer = new FileWriter(javaExample);
            LOGGER.info("Java Example creating");
            writer.write("""
                        import io.github.kadir1243.kajava.api.KajavaPlugin;
                        
                        public class Example implements KajavaPlugin {
                        
                            @Override
                            public void run() {
                                LOGGER.info("Hello From Example!");
                            }
                        }
                        """
            );
            writer.close();
        }
    }
    public static void createGroovyExample() throws IOException {
        File groovyExample = new File(FabricLoader.getInstance().getConfigDir().toString() + "\\" + Init.MODID + "\\source\\groovy\\Example.groovy");
        if (groovyExample.createNewFile()) {
            FileWriter writer = new FileWriter(groovyExample);
            LOGGER.info("Groovy Example creating");
            writer.write("""
                        import io.github.kadir1243.kajava.api.KajavaPlugin
                        
                        class Example implements KajavaPlugin {
                            void run() {
                                LOGGER.info("Hello From Groovy Example!")
                            }
                        }
                        """
            );
            writer.close();
        }
    }
}
