package org.spongepowered.asm.mixin.transformer;

import io.github.kadir1243.kajava.Init;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collections;
import java.util.List;

/**
 * We need edit mixin classes to add new mixins from {@linkplain io.github.kadir1243.kajava.ConfigJava#mixinClasses here} <p>
 * package location is here because {@linkplain MixinConfig target} class is private and final class
 */
@Mixin(value = MixinConfig.class,remap = false)
public class CustomMixinClassAdding {
    @Inject(method = "prepareMixins",at = @At(value = "HEAD",remap = false),remap = false)
    private void addNewMixins(List<String> mixinClassesp, boolean ignorePlugin, CallbackInfo ci) {
        String[] configMixinClasses = Init.getConfig().mixinClasses;
        if (configMixinClasses != null && configMixinClasses.length > 0) {
            Collections.addAll(mixinClassesp, configMixinClasses);
        }
    }
}
