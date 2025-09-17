package dev.puzzleshq.buildsrc.zomboid.tasks.loader;

import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.SourceSetContainer;

import java.util.ArrayList;

public class BuildClientJarTask extends ShadowJar {

    public BuildClientJarTask() {
        setGroup("zomboid");
        mergeServiceFiles();
        setConfigurations(new ArrayList<>(){{
            add(getProject().getConfigurations().getByName("clientBundle"));
        }});

        getArchiveClassifier().set("client");
        SourceSetContainer extension = getProject().getExtensions().getByType(SourceSetContainer.class);
        SourceSet sourceSet = extension.getByName("client");
        from(sourceSet.getOutput());
    }

}
