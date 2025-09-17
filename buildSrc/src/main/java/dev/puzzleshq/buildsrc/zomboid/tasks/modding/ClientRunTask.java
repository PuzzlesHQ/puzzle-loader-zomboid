package dev.puzzleshq.buildsrc.zomboid.tasks.modding;

import dev.puzzleshq.buildsrc.zomboid.ZomboidPlugin;
import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.file.FileCollection;
import org.gradle.api.plugins.JavaPluginExtension;
import org.gradle.api.tasks.JavaExec;
import org.gradle.api.tasks.SourceSet;

import java.util.List;

public class ClientRunTask extends JavaExec {

    public ClientRunTask() {
        super();

        setGroup("zomboid");

        setJvmArgs((List<String>) ZomboidPlugin.json.get("vmArgs"));
        setWorkingDir(ZomboidPlugin.zomboidPath);

        getMainClass().set("dev.puzzleshq.puzzleloader.loader.launch.pieces.ClientPiece");

        ConfigurableFileCollection collection = (ConfigurableFileCollection) getClasspath();
        JavaPluginExtension extension = getProject().getExtensions().getByType(JavaPluginExtension.class);
        SourceSet clientSourceSet = extension.getSourceSets().getByName("client");
        SourceSet commonSourceSet = extension.getSourceSets().getByName("common");

        FileCollection clientCollection = clientSourceSet.getRuntimeClasspath();
        FileCollection commonCollection = commonSourceSet.getRuntimeClasspath();

        collection.from(clientCollection.getFiles());
        collection.from(commonCollection.getFiles());
    }

}
