package dev.puzzleshq.buildsrc.zomboid;

import dev.puzzleshq.buildsrc.steam.SteamAppLocator;
import dev.puzzleshq.buildsrc.zomboid.tasks.ClientRunTask;
import dev.puzzleshq.buildsrc.zomboid.tasks.ServerRunTask;
import groovy.json.JsonSlurper;
import org.apache.groovy.json.internal.LazyMap;
import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.dsl.DependencyHandler;
import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.file.SyncSpec;
import org.gradle.api.tasks.TaskContainer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ZomboidPlugin implements Plugin<Project> {

    public static final int steamAppId = 108600;
    public static Path zomboidPath;
    public static File gameJar;
    public static LazyMap json;

    @Override
    public void apply(Project target) {
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
        taskContainer.register("runClient", ClientRunTask.class);
        taskContainer.register("runServer", ServerRunTask.class);

        taskContainer.register("generateZomboidJar", task -> {
            task.setGroup("zomboid");

            task.doLast(task1 -> {
                try {
                    ZomboidUtil.convertToJar(
                            target,
                            gameJar,
                            new File(zomboidPath.toString())
                    );
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        });

        taskContainer.register("deleteZomboidJar", task -> {
            task.setGroup("zomboid");

            task.doLast(task1 -> {
                gameJar.delete();
            });
        });

    }

}
