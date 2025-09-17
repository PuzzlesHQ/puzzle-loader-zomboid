package dev.puzzleshq.buildsrc.zomboid.tasks.loader;

import org.gradle.api.DefaultTask;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.ConfigurationContainer;
import org.gradle.api.artifacts.Dependency;
import org.gradle.api.tasks.TaskAction;
import org.hjson.JsonArray;
import org.hjson.JsonObject;
import org.hjson.Stringify;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BuildDependenciesJsonTask extends DefaultTask {

    public BuildDependenciesJsonTask() {
        setGroup("zomboid");
    }

    private static JsonArray createDeps(
            Configuration implDeps
            ) {
        JsonArray array = new JsonArray();

        for (Dependency dependency : implDeps.getAllDependencies()) {
            JsonObject dep = new JsonObject();
            dep.add("groupId", dependency.getGroup());
            dep.add("name", dependency.getName());
            dep.add("version", dependency.getVersion());
            dep.add("type", "implementation");

            array.add(dep);
        }

        return array;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @TaskAction
    public void exec() {
        File depJson = getProject().file("dependencies.json");

        ConfigurationContainer configurations = getProject().getConfigurations();

        JsonObject object = new JsonObject();
        object.add("client", createDeps(configurations.getByName("clientBundle")));
        object.add("common", createDeps(configurations.getByName("commonBundle")));
        object.add("server", createDeps(configurations.getByName("serverBundle")));

        try {
            depJson.createNewFile();

            FileOutputStream stream = new FileOutputStream(depJson);
            stream.write(object.toString(Stringify.FORMATTED).getBytes());
            stream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
