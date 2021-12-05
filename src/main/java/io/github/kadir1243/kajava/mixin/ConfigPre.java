package io.github.kadir1243.kajava.mixin;

import java.util.Arrays;

class ConfigPre {
    public String[] mixinClasses = new String[]{};

    @Override
    public String toString() {
        return "mixinClasses=" + Arrays.toString(mixinClasses);
    }
}
