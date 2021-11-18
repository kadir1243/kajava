package io.github.kadir1243.kajava.api;

import io.github.kadir1243.kajava.Init;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface KajavaPlugin {
    /**
     * Default Logger
     */
    Logger LOGGER = LogManager.getLogger(Init.MODID);

    /**
     * Runnable code (for client and server) if you want to add common things use this method
     */
    void run();

    /**
     * {@link #run() runs} common code if true
     */
    default boolean execute() {
        return true;
    }

    /**
     * Runnable code (for {@linkplain net.minecraft.client.MinecraftClient client}) if you want to add things for only {@linkplain net.fabricmc.api.EnvType#CLIENT client} use this <p>
     * if you want to edit this method use {@linkplain Override} way
     */
    default void runClient() {}

    /**
     * {@link #runClient() runs} client code if true
     */
    default boolean executeClient() {
        return true;
    }

    /**
     * Runnable code (for {@linkplain net.minecraft.server.MinecraftServer server}) if you want to add things for only {@linkplain net.fabricmc.api.EnvType#SERVER server} use this <p>
     * if you want to edit this method use {@linkplain Override} way
     */
    default void runServer() {}

    /**
     * {@link #runServer() runs} server code if true
     */
    default boolean executeServer() {
        return true;
    }
}
