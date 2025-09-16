package dev.puzzleshq.buildsrc.zomboid.tasks.loader;

import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar;
import org.gradle.api.plugins.JavaPluginExtension;
import org.gradle.api.tasks.SourceSet;

public class BuildCommonJarTask extends ShadowJar {

    public BuildCommonJarTask() {
        setGroup("zomboid");

        mergeServiceFiles();

        getArchiveClassifier().set("common");
        JavaPluginExtension extension = getProject().getExtensions().getByType(JavaPluginExtension.class);
        SourceSet sourceSet = extension.getSourceSets().getByName("common");
        from(sourceSet.getOutput());
    }

}
