package com.robust.basis.file;

import java.io.IOException;
import java.nio.file.*;
import java.util.Iterator;

/**
 * Created by bintao on 2017/6/20.
 */
public class FileOperate {

    public static void main(String[] args) throws IOException {
        Path directory = Paths.get("I:\\movie\\架构师视频1\\私塾在线java架构师-两阶段\\私塾在线java架构师148讲视频教程阶段一\\架构师148讲视频教程(41-50讲)");
        System.out.println(directory.toFile().isDirectory());

        System.out.println();


        DirectoryStream ds = Files.newDirectoryStream(directory);
        Iterator iterator = ds.iterator();
        while (iterator.hasNext()){
            Path file = (Path) iterator.next();
            if(!Files.isDirectory(file)) {
                String name = file.getFileName().toString();
                if(name.startsWith("backup_")) {
                    String srcName = name.substring(7, name.length());
                    System.out.println("currentName is:" + name);
                    System.out.println("srcName is :" + srcName);
                    Path src = directory.resolve(srcName);
                    if(Files.exists(src)) {
                        Files.delete(file);
                    } else {
                        Path path = Files.copy(file, src);
                        System.out.println(path);
                    }
                }
            }
//            System.out.println(file.getFileName());
        }

        /*DirectoryStream ds1 = Files.newDirectoryStream(directory, "[\u4E00-\u9FA5]");
        Iterator iterator1 = ds1.iterator();
        while (iterator1.hasNext()){
            Path file = (Path) iterator1.next();
            System.out.println(file.getFileName());
        }*/

//        Files.copy(path, Paths.get("F:\\bookCopy.txt"));
//        Files.copy(path, Paths.get("F:\\bookCopy.txt"), StandardCopyOption.REPLACE_EXISTING);

    }
}
