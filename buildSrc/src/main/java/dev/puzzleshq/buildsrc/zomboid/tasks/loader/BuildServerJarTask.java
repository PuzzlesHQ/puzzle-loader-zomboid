package dev.puzzleshq.buildsrc.zomboid.tasks.loader;

import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar;
import org.gradle.api.plugins.JavaPluginExtension;
import org.gradle.api.tasks.SourceSet;

public class BuildServerJarTask extends ShadowJar {

    public BuildServerJarTask() {
        setGroup("zomboid");

        mergeServiceFiles();

        getArchiveClassifier().set("server");
        JavaPluginExtension extension = getProject().getExtensions().getByType(JavaPluginExtension.class);
        SourceSet sourceSet = extension.getSourceSets().getByName("server");
        from(sourceSet.getOutput());
    }

}
