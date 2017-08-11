package com.robust.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by bintao on 2017/8/8.
 */
public class ReadFileTest {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("E:\\yusys\\data.log");
        Set<String> list = new HashSet<>();
        Files.lines(path).parallel().forEach(
                s -> {
                    if(s.indexOf("request ip is:") > -1){
                        s = s.substring(s.lastIndexOf("-") + 1 , s.length());
                        s = s.substring(0, s.lastIndexOf("."));
                        s = s.substring(0, s.lastIndexOf("."));
                        list.add(s);
                    }
                }
        );
        list.forEach(s -> System.out.println(s));
        System.out.println(list.size());


    }
}
