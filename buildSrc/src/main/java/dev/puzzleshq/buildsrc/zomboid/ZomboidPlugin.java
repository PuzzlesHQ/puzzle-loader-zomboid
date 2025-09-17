package dev.puzzleshq.buildsrc.zomboid;

import dev.puzzleshq.buildsrc.steam.SteamAppLocator;
import dev.puzzleshq.buildsrc.zomboid.tasks.modding.ClientRunTask;
import dev.puzzleshq.buildsrc.zomboid.tasks.modding.ServerRunTask;
import dev.puzzleshq.buildsrc.zomboid.tasks.configurations.Configurations;
import dev.puzzleshq.buildsrc.zomboid.tasks.loader.LoaderTasks;
import dev.puzzleshq.buildsrc.zomboid.tasks.modding.GeneralModdingTasks;
import groovy.json.JsonSlurper;
import org.apache.groovy.json.internal.LazyMap;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.dsl.DependencyHandler;
import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.tasks.TaskContainer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class ZomboidPlugin implements Plugin<Project> {

    public static final int steamAppId = 108600;
    public static Path zomboidPath;
    public static File gameJar;
    public static LazyMap json;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public void apply(Project target) {
        Configurations.setup(target.getConfigurations());

        zomboidPath = SteamAppLocator.locate(steamAppId);
        if (zomboidPath == null) throw new RuntimeException("You must have Project Zomboid installed via Steam");
        zomboidPath = zomboidPath.toAbsolutePath();

        try {
            json = (LazyMap) new JsonSlurper().parse(Path.of(zomboidPath.toString(), "ProjectZomboid64.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        gameJar = target.file("build/projectZomboid.jar");
        gameJar.getParentFile().mkdirs();

        ZomboidUtil.addJarToDeps(target, gameJar);
        ZomboidUtil.addClasspath(target, json, zomboidPath);

        ConfigurableFileCollection configurableFileCollection = target.files(zomboidPath);

        DependencyHandler dependencyHandler = target.getDependencies();
        dependencyHandler.add("clientRuntimeOnly", configurableFileCollection);
        dependencyHandler.add("serverRuntimeOnly", configurableFileCollection);
        dependencyHandler.add("commonRuntimeOnly", configurableFileCollection);

        TaskContainer taskContainer = target.getTasks();
        Map<String, ?> properties = target.getProperties();

        GeneralModdingTasks.load(taskContainer);
        if (properties.containsKey("modding_environment") && properties.get("modding_environment").equals("loader")) {
            LoaderTasks.load(taskContainer);
        }

    }

}
