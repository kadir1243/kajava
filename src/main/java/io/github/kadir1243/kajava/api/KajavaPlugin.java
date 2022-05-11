package io.github.kadir1243.kajava.api;

import io.github.kadir1243.kajava.Init;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
public interface KajavaPlugin {
    /**
     * Default Logger
     */
    Logger LOGGER = LoggerFactory.getLogger(Init.MODID);

    /**
     * Runnable code (for client and server) if you want to add common things use this method
     */
    void run();

    /**
     * {@link #run() runs} common code if true
     * @deprecated use {@code if(statement)} in {@link #run} instead
     */
    @Deprecated(forRemoval = true, since = "0.0.7")
    default boolean condition() {
        return true;
    }

    /**
     * Runnable code (for {@linkplain net.minecraft.client.MinecraftClient client}) if you want to add things for only {@linkplain net.fabricmc.api.EnvType#CLIENT client} use this <p>
     * if you want to edit this method use {@linkplain Override} way
     */
    default void runClient() {}

    /**
     * {@link #runClient() runs} client code if true
     * @deprecated use {@code if(statement)} in {@link #runClient} instead
     */
    @Deprecated(forRemoval = true, since = "0.0.7")
    default boolean clientCondition() {
        return true;
    }

    /**
     * Runnable code (for {@linkplain net.minecraft.server.MinecraftServer server}) if you want to add things for only {@linkplain net.fabricmc.api.EnvType#SERVER server} use this <p>
     * if you want to edit this method use {@linkplain Override} way
     */
    default void runServer() {}

    /**
     * {@link #runServer() runs} server code if true
     * @deprecated use {@code if(statement)} in {@link #runServer} instead
     */
    @Deprecated(forRemoval = true, since = "0.0.7")
    default boolean serverCondition() {
        return true;
    }
}
