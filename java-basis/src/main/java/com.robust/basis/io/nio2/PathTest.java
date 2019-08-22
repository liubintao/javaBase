package com.robust.basis.io.nio2;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class PathTest {
    public static void main(String[] args) throws IOException {
//        createFile();
        batchRead();
    }

    public static void batchRead() throws IOException {
        Path path = Paths.get("I:\\gitRepo\\btliu\\java-base\\java-basis\\");
        path = path.resolve("contentSwitch.txt");
        System.out.println(Files.readAttributes(path, "*"));
    }

    public static void createFile() throws IOException {
        Path path = Paths.get("I:\\gitRepo\\btliu\\java-base\\java-basis\\");
        path = path.resolve("contentSwitch.txt");
        path = Files.createFile(path);
        System.out.println(path.getFileName());
//        Files.delete(path);
    }

    public static void walkTree() throws IOException {
        Path path = Paths.get("I:\\gitRepo\\btliu\\java-base\\java-basis\\");
//        Files.walkFileTree(path, new FindJavaVisitor());
        Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if(file.toString().endsWith(".java")){
                    System.out.println(file.getFileName());
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }

    static class FindJavaVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            System.out.println(file.getFileName());
            return FileVisitResult.CONTINUE;
        }
    }
}
