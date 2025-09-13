package dev.puzzleshq.buildsrc;

import kotlin.text.Charsets;
import org.apache.groovy.json.internal.LazyMap;
import org.gradle.api.Project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZomboidUtil {

    public static void addClasspath(Project project, LazyMap json, Path zomboidPath) {
        List<Object> classpath = (List<Object>) json.get("classpath");
        for (Object file : classpath) {
            if (file == ".") continue;

            Path path = zomboidPath.toAbsolutePath().resolve((String) file);
            Path absPath = path.toAbsolutePath();

            project.getDependencies().add("clientImplementation", project.files(absPath));
            project.getDependencies().add("commonImplementation", project.files(absPath));
            project.getDependencies().add("serverImplementation", project.files(absPath));
        }
    }

    public static void convertToJar(Project project, File jar, File dir) throws IOException {
        if (!jar.exists()) {
            jar.createNewFile();

            ZipOutputStream stream = new ZipOutputStream(new FileOutputStream(jar));
            stream.putNextEntry(new ZipEntry("META-INF/MANIFEST.MF"));
            stream.write("Manifest-Version: 1.0\n".getBytes(Charsets.UTF_8));

            recurse(stream, "", dir);
            stream.close();
        }

        String absPath = jar.getAbsolutePath();

        project.getDependencies().add("clientCompileOnly", project.files(absPath));
        project.getDependencies().add("commonCompileOnly", project.files(absPath));
        project.getDependencies().add("serverCompileOnly", project.files(absPath));
    }

    private static void addEntry(ZipOutputStream zip, String dir, File file) throws IOException {
        zip.putNextEntry(new ZipEntry((dir.isEmpty() ? "" : (dir + "/")) + file.getName()));
        FileInputStream fis = new FileInputStream(file);
        zip.write(fis.readAllBytes());
        fis.close();
    }

    private static void recurse(ZipOutputStream file, String parent, File d) throws IOException {
        if (d.isDirectory()) {
            for (File f : Objects.requireNonNull(d.listFiles())) {
                if (f.isDirectory()) {
                    recurse(file, (parent.isEmpty() ? "" : (parent + "/")) + f.getName(), f);
                } else {
                    if (!f.getName().contains(".class")) continue;
                    addEntry(file, parent, f);
                }
            }
            return;
        }
        addEntry(file, parent, d);
    }

}
