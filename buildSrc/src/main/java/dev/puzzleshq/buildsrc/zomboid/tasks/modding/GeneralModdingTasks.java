package dev.puzzleshq.buildsrc.zomboid.tasks.modding;

import org.gradle.api.tasks.TaskContainer;

public class GeneralModdingTasks {

    public static void load(TaskContainer taskContainer) {
        taskContainer.register("buildClientBundleJar", BuildClientBundledJarTask.class);
        taskContainer.register("buildServerBundleJar", BuildServerBundledJarTask.class);
        taskContainer.register("createZomboidModdingJar", GenerateZomboidJarTask.class);
        taskContainer.register("removeZomboidModdingJar", DeleteZomboidJarTask.class);
        taskContainer.register("runClient", ClientRunTask.class);
        taskContainer.register("runServer", ServerRunTask.class);
    }

}
