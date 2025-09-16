package dev.puzzleshq.buildsrc.zomboid.tasks.loader;

import org.gradle.api.tasks.TaskContainer;

public class LoaderTasks {

    public static void load(TaskContainer taskContainer) {
        taskContainer.register("buildClientJar", BuildClientJarTask.class);
        taskContainer.register("buildServerJar", BuildServerJarTask.class);
        taskContainer.register("buildCommonJar", BuildCommonJarTask.class);
        taskContainer.register("buildSourcesJar", BuildSourcesJarTask.class);
        taskContainer.register("mkDeps", BuildDependenciesJsonTask.class);
    }

}
