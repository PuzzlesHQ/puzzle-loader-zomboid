package dev.puzzleshq.buildsrc.zomboid.tasks.configurations;

import org.gradle.api.Action;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.ConfigurationContainer;

public class Configurations {

    public static void setup(ConfigurationContainer configurations) {
        Action<? super Configuration> bundleAction = (c) -> {
            c.setCanBeResolved(true);
            c.setCanBeConsumed(true);
        };

        Configuration clientBundleConfiguration = configurations.register("clientBundle", bundleAction).get();
        Configuration serverBundleConfiguration = configurations.register("serverBundle", bundleAction).get();
        Configuration commonBundleConfiguration = configurations.register("commonBundle", bundleAction).get();

        configurations.getByName("clientImplementation").extendsFrom(clientBundleConfiguration);
        configurations.getByName("serverImplementation").extendsFrom(serverBundleConfiguration);
        configurations.getByName("commonImplementation").extendsFrom(commonBundleConfiguration);
    }

}
