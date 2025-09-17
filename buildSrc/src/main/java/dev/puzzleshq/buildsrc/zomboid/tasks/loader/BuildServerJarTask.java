package dev.puzzleshq.buildsrc.zomboid.tasks.loader;

import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.SourceSetContainer;

import java.util.ArrayList;

public class BuildServerJarTask extends ShadowJar {

    public BuildServerJarTask() {
        setGroup("zomboid");
        setConfigurations(new ArrayList<>(){{
            add(getProject().getConfigurations().getByName("serverBundle"));
        }});

        getArchiveClassifier().set("server");
        SourceSetContainer extension = getProject().getExtensions().getByType(SourceSetContainer.class);
        SourceSet sourceSet = extension.getByName("server");
        from(sourceSet.getOutput());
    }

}
