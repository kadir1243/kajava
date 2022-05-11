package io.github.kadir1243.kajava;

import io.github.kadir1243.kajava.loader.Examples;
import io.github.kadir1243.kajava.loader.RuntimeClassLoader;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Init {
    public static final String MODID = "kajava";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
    public static boolean isClient = FabricLoader.getInstance().getEnvironmentType().equals(EnvType.CLIENT);

    @SuppressWarnings("CommentedOutCode")
    public static void commonInit() {
        AutoConfig.register(ConfigJava.class, GsonConfigSerializer::new);
        String normalConfigDirString = FabricLoader.getInstance().getConfigDir().toString();
        String modConfigDir = normalConfigDirString + "\\" + MODID;
        String sourcesDirString = modConfigDir + "\\source\\";
        String javaDirString =  sourcesDirString + "java";
        String resourceDirString = modConfigDir + "\\resouces";
        // String mappingFileString = modConfigDir + "\\yarn.jar"; // TODO: Remapping
        String groovyDirString = sourcesDirString + "groovy";
        File javaFile = new File(javaDirString);
        File groovyFile = new File(groovyDirString);
        // File mappingFile = new File(mappingFileString);
        File resourcesFile = new File(resourceDirString);
        if (javaFile.mkdirs() || resourcesFile.mkdirs() || groovyFile.mkdirs()) LOGGER.info("Creating Config");
        Examples.init();
        /*if (!mappingFile.exists() || getConfig().yarnRedownload) { // TODO: Remapping
                    if (getConfig().yarnRedownload) getConfig().yarnRedownload = false;
                    else LOGGER.warn("Can't found mappings");
                    LOGGER.warn("Mappings ReDownloading");
                    // Remapper.YarnMappings.download(getConfig().yarnGroupId, getConfig().yarnArtifactId,getConfig().yarnVersion,mappingFile); // TODO : Remapping
                }*/
        RuntimeClassLoader.run(javaDirString,groovyDirString);
    }

    @Environment(EnvType.CLIENT)
    public static void clientInit() {
    }

    @Environment(EnvType.SERVER)
    public static void serverInit() {
    }

    public static ConfigJava getConfig() {
        return AutoConfig.getConfigHolder(ConfigJava.class).getConfig();
    }
}
