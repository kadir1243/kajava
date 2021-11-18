package io.github.kadir1243.kajava;

import io.github.kadir1243.kajava.loader.ClassLoadRuntime;
import io.github.kadir1243.kajava.loader.Remapper;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.nio.file.Path;

public class Init {
    public static final String MODID = "kajava";
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static Boolean isClient;

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
        if (!mappingFile.exists() || getConfig().yarnRedownload) {
            if (getConfig().yarnRedownload) getConfig().yarnRedownload = false;
            else LOGGER.warn("Can't found mappings");
            LOGGER.warn("Mappings ReDownloading");
            Remapper.YarnMappings.download(getConfig().yarnGroupId, getConfig().yarnArtifactId,getConfig().yarnVersion,mappingFile);
        }
        if (javaFile.listFiles() != null) ClassLoadRuntime.run(javaDirString);
    }

    public static void clientInit() {
        isClient = true;
    }

    public static void serverInit() {
        isClient = false;
    }

    public static ConfigJava getConfig() {
        return AutoConfig.getConfigHolder(ConfigJava.class).getConfig();
    }
}
