package com.robust.basis.io.nio2;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class FileSystemTest {
    public static void main(String[] args) {
        FileSystem fs = FileSystems.getDefault();
        System.out.println(fs.toString());
    }
}
