package dev.puzzleshq.buildsrc.zomboid.tasks.loader;

import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar;
import org.gradle.api.plugins.JavaPluginExtension;
import org.gradle.api.tasks.SourceSet;

public class BuildSourcesJarTask extends ShadowJar {

    public BuildSourcesJarTask() {
        setGroup("zomboid");

        getArchiveClassifier().set("sources");

        JavaPluginExtension extension = getProject().getExtensions().getByType(JavaPluginExtension.class);
        SourceSet clientSourceSet = extension.getSourceSets().getByName("client");
        SourceSet serverSourceSet = extension.getSourceSets().getByName("server");
        SourceSet commonSourceSet = extension.getSourceSets().getByName("common");
        from(clientSourceSet.getAllSource());
        from(serverSourceSet.getAllSource());
        from(commonSourceSet.getAllSource());
    }

}
