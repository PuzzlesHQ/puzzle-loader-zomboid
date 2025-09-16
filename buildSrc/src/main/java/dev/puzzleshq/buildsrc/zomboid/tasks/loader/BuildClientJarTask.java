package dev.puzzleshq.buildsrc.zomboid.tasks.loader;

import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar;
import org.gradle.api.plugins.JavaPluginExtension;
import org.gradle.api.tasks.SourceSet;

public class BuildClientJarTask extends ShadowJar {

    public BuildClientJarTask() {
        setGroup("zomboid");

        mergeServiceFiles();

        getArchiveClassifier().set("client");
        JavaPluginExtension extension = getProject().getExtensions().getByType(JavaPluginExtension.class);
        SourceSet sourceSet = extension.getSourceSets().getByName("client");
        from(sourceSet.getOutput());
    }

}
