package dev.puzzleshq.buildsrc.zomboid.tasks.loader;

import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.SourceSetContainer;

public class BuildSourcesJarTask extends ShadowJar {

    public BuildSourcesJarTask() {
        setGroup("zomboid");

        getArchiveClassifier().set("sources");

        SourceSetContainer extension = getProject().getExtensions().getByType(SourceSetContainer.class);
        SourceSet clientSourceSet = extension.getByName("client");
        SourceSet serverSourceSet = extension.getByName("server");
        SourceSet commonSourceSet = extension.getByName("common");
        from(clientSourceSet.getAllSource());
        from(serverSourceSet.getAllSource());
        from(commonSourceSet.getAllSource());
    }

}
