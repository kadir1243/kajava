package io.github.kadir1243.kajava;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = Init.MODID)
public class ConfigJava implements ConfigData {
    public String[] runnableClasses = new String[]{"Example"};
    @Comment("Compiles All Java Files")
    public boolean javaCompile = true;
    @Comment("Runs All Java Files")
    public boolean javaRuns = true;
    public boolean createExample = true;
    @Comment("Runs All Groovy Files")
    public boolean groovyRuns = true;
/*public boolean remapJava = false; // TODO: Remapping
    public String mavenLink = "https://maven.fabricmc.net/";
    public String yarnGroupId = "net.fabricmc";
    public String yarnArtifactId = "yarn";
    public String yarnVersion = "1.17.1+build.64";
    public boolean yarnV2 = true;
    public boolean yarnRedownload = false;
    @Comment("Can be official or yarn")
    public String mappingType = "official";*/
}
