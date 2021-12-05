package io.github.kadir1243.kajava.mixin;

import com.google.gson.Gson;
import io.github.kadir1243.kajava.Init;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Plugin implements IMixinConfigPlugin {

    @Override
    public void onLoad(String mixinPackage) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        /*try { // FIXME: Wait for fixing everytime adding "." to packages
            Logger logger = LogManager.getLogger(Init.MODID + " Mixin Plugin");
            File mixinConfigFile = new File(FabricLoader.getInstance().getConfigDir().toString() + "\\" + Init.MODID + "\\mixinClasses.json");
            if (mixinConfigFile.createNewFile()) {
                logger.info("Config Created");
                FileWriter writer = new FileWriter(mixinConfigFile);
                writer.write("""
                  {
                    "mixinClasses": [
                       
                    ]
                  }
                """
                );
                writer.close();
            }
            Gson gson = new Gson();
            StringBuilder addationalMixinsBuilder = new StringBuilder();
            Scanner scanner = new Scanner(mixinConfigFile);
            while (scanner.hasNext()) addationalMixinsBuilder.append(scanner.next());
            scanner.close();
            String addationalMixins = addationalMixinsBuilder.toString();
            ConfigPre configPre = gson.fromJson(addationalMixins, ConfigPre.class);
            logger.info("Adding mixin classes");
            return Arrays.asList(configPre.mixinClasses);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
