package dev.puzzleshq.buildsrc.widen;

import org.gradle.api.artifacts.transform.InputArtifact;
import org.gradle.api.artifacts.transform.TransformAction;
import org.gradle.api.artifacts.transform.TransformOutputs;
import org.gradle.api.artifacts.transform.TransformParameters;
import org.gradle.api.file.FileSystemLocation;
import org.gradle.api.provider.Provider;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class JarTransformer implements TransformAction<TransformParameters.None> {

    @InputArtifact
    public abstract Provider<FileSystemLocation> getInputArtifact();

    @Override
    public void transform(TransformOutputs outputs) {
        var inp = getInputArtifact().get().getAsFile();
        var out = outputs.file(inp.getName().replace(".jar", "-transformed.jar"));

        try {
//            List<Relocation> rules = new ArrayList<>();
//
//            rules.add(new Relocation("org.objectweb", "bundled.org.objectweb"));
//            rules.add(new Relocation("org.spongepowered.include", "bundled"));
//
//            JarRelocator relocator = new JarRelocator(inp, out, rules);
//            relocator.run();

            GenericTransformer.transform(inp, out);
        } catch (Exception ignore) {
            try {
                FileOutputStream stream = new FileOutputStream(out);
                FileInputStream stream1 = new FileInputStream(inp);
                stream.write(stream1.readAllBytes());
                stream.close();
                stream1.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
