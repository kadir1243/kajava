package io.github.kadir1243.kajava;

import io.github.kadir1243.kajava.loader.ClassLoadRuntime;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class Init {
    public static final String MODID = "kajava";
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static boolean isClient = FabricLoader.getInstance().getEnvironmentType().equals(EnvType.CLIENT);

    public static void commonInit() {
        AutoConfig.register(ConfigJava.class, GsonConfigSerializer::new);
        Path normalConfigDirPath = FabricLoader.getInstance().getConfigDir();
        String normalConfigDirString = normalConfigDirPath.toString();
        String modConfigDir = normalConfigDirString + "\\" + MODID;
        String javaDirString =  modConfigDir + "\\source\\java";
        String resourceDirString = modConfigDir + "\\resouces";
        String mappingFileString = modConfigDir + "\\yarn.jar";
        File javaFile = new File(javaDirString);
        File mappingFile = new File(mappingFileString);
        File resourcesFile = new File(resourceDirString);
        if (javaFile.mkdirs() || resourcesFile.mkdirs()) LOGGER.info("Creating Config");
        try {
            File exampleFile = new File(javaDirString + "\\Example.java");
            if (exampleFile.createNewFile() && getConfig().createExample) {
                FileWriter writer = new FileWriter(exampleFile);
                LOGGER.info("Example creating");
                writer.write("""
                        import io.github.kadir1243.kajava.api.KajavaPlugin;
                        
                        public class Example implements KajavaPlugin {
                        
                            @Override
                            public void run() {
                                LOGGER.info("Hello From Example!");
                            }
                            
                            @Override
                            public boolean condition() {
                               return true; //Run code if you turn this to false class will not run
                            }
                        }
                        """
                );
                writer.close();
            }
                /*if (!mappingFile.exists() || getConfig().yarnRedownload) { // TODO: Remapping
                    if (getConfig().yarnRedownload) getConfig().yarnRedownload = false;
                    else LOGGER.warn("Can't found mappings");
                    LOGGER.warn("Mappings ReDownloading");
                    // Remapper.YarnMappings.download(getConfig().yarnGroupId, getConfig().yarnArtifactId,getConfig().yarnVersion,mappingFile); // TODO : Remapping
                }*/
        } catch (IOException e) {e.printStackTrace();}
        if (javaFile.listFiles() != null) ClassLoadRuntime.run(javaDirString);
    }

    public static void clientInit() {

    }

    public static void serverInit() {

    }

    public static ConfigJava getConfig() {
        return AutoConfig.getConfigHolder(ConfigJava.class).getConfig();
    }
}
