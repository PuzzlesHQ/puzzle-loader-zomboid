package dev.puzzleshq.buildsrc.widen;

import dev.puzzleshq.accesswriter.transformers.AccessTransformerASM;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class GenericTransformer {

    public static void transform(File in, File out) {
        try {
            InputStream byteStream = new FileInputStream(in);
            byte[] bytes = byteStream.readAllBytes();
            byteStream.close();

            ZipInputStream input = new ZipInputStream(new ByteArrayInputStream(bytes));
            ZipOutputStream output = new ZipOutputStream(new FileOutputStream(out));
            ZipEntry entry = input.getNextEntry();
            while (entry != null) {
                String entryName = entry.getName();

                if (entryName.contains("module-info") || entryName.contains("package-info")) {
                    output.putNextEntry(entry);
                    output.write(input.readAllBytes());
                } else if (entryName.endsWith(".class")) {
                    byte[] classBytes = transformClass(entryName, input.readAllBytes());
                    if (classBytes != null) {
                        output.putNextEntry(entry);
                        output.write(classBytes);
                    }
                } else {
                    output.putNextEntry(entry);
                    output.write(input.readAllBytes());
                }

                entry = input.getNextEntry();
            }
            input.close();
            output.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] transformClass(String entryName, byte[] bytes) {
        if (entryName.contains("io/netty")) return bytes;
        if (entryName.contains("com/google")) return bytes;
        if (entryName.contains("org/apache/logging")) return bytes;

        ClassReader reader = new ClassReader(bytes);
        ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES);

        InterfaceInjector interfaceInjector = new InterfaceInjector(writer);
        AccessTransformerASM accessTransformer = new AccessTransformerASM(interfaceInjector);

        reader.accept(accessTransformer, 0);

        return writer.toByteArray();
    }

}
