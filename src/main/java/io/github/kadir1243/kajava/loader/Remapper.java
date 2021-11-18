package io.github.kadir1243.kajava.loader;

import io.github.kadir1243.kajava.Init;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.tinyremapper.Main;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;

/*public class Remapper {
    public static final Logger LOGGER = LogManager.getLogger(Init.MODID + " Remapper System");

    public static void run(File[] files,File mapping) {
        for (File file : files) {
            try {
                remapAllJava(file,mapping);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public static void remapAllJava(File file, File mapping) {
        if (!FabricLoader.getInstance().isDevelopmentEnvironment()) {
            remapJavaClassImports(file);
        }
        remapJavaFields(file,mapping);
        remapJavaMethods(file);
    }

    public static void remapJavaFields(File file, File mapping) {
        // TODO: Add Support for remapping fields
    }

    public static void remapJavaMethods(File file) {
        // TODO: Add Support for remapping methods
    }

    public static void remapJavaClassImports(File file) {
        // TODO: Add Support for remapping class imports and class names
    }
    // TODO: Add Other Programming Languages to remap

    public static class YarnMappings {
        public static void download(String groupId,String artifactId,String version,File downloadedFile) {
            String url = gradle2Url(groupId,artifactId,version,Init.getConfig().mavenLink);
            CloseableHttpClient client = HttpClients.createDefault();
            try (CloseableHttpResponse response = client.execute(new HttpGet(url))) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    try (FileOutputStream outstream = new FileOutputStream(downloadedFile)) {
                        entity.writeTo(outstream);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public static String gradle2Url(String groupId,String artifactId,String version,String maven) {
            StringBuilder builder = new StringBuilder();
            String url = groupId.replaceAll("\\.", "/") + "/" + artifactId + "/" + (Init.getConfig().yarnV2 ? version + "-v2" : version) + ".jar";
            if (!maven.endsWith("/")) builder.append(maven).append("/");
            else builder.append(maven);
            builder.append(url);
            LOGGER.info("Saved mappings: " + builder);
            return builder.toString();
        }
    }
}*/
