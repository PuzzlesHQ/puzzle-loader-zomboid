package dev.puzzleshq.buildsrc.zomboid.tasks.modding;

import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.SourceSetContainer;

import java.util.ArrayList;

public class BuildServerBundledJarTask extends ShadowJar {

    public BuildServerBundledJarTask() {
        setGroup("zomboid");
        mergeServiceFiles();
        setConfigurations(new ArrayList<>(){{
            add(getProject().getConfigurations().getByName("serverBundle"));
            add(getProject().getConfigurations().getByName("commonBundle"));
        }});

        getArchiveClassifier().set("server-bundle");
        SourceSetContainer extension = getProject().getExtensions().getByType(SourceSetContainer.class);
        SourceSet sourceSetA = extension.getByName("server");
        from(sourceSetA.getOutput());
        SourceSet sourceSetB = extension.getByName("common");
        from(sourceSetB.getOutput());
    }

}
