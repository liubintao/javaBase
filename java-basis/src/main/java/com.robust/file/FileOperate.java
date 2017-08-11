package com.robust.file;

import java.io.IOException;
import java.nio.file.*;
import java.util.Iterator;

/**
 * Created by bintao on 2017/6/20.
 */
public class FileOperate {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("F:\\test.txt");
        System.out.println(path.toFile().exists());
        System.out.println(path.toFile().isDirectory());
        Path directory = Paths.get("F:\\book");
        System.out.println(directory.toFile().isDirectory());
        System.out.println(path.getFileName().toString());

        System.out.println();
        Files.lines(path).parallel().forEach((s) -> System.out.println(s));


        DirectoryStream ds = Files.newDirectoryStream(directory);
        Iterator iterator = ds.iterator();
        while (iterator.hasNext()){
            Path file = (Path) iterator.next();
            System.out.println(file.getFileName());
        }

        /*DirectoryStream ds1 = Files.newDirectoryStream(directory, "[\u4E00-\u9FA5]");
        Iterator iterator1 = ds1.iterator();
        while (iterator1.hasNext()){
            Path file = (Path) iterator1.next();
            System.out.println(file.getFileName());
        }*/

//        Files.copy(path, Paths.get("F:\\bookCopy.txt"));
//        Files.copy(path, Paths.get("F:\\bookCopy.txt"), StandardCopyOption.REPLACE_EXISTING);

        Files.lines(path).forEach((p) -> System.out.println(p.toString()));
    }
}
