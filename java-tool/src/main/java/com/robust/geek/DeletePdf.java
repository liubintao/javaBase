package com.robust.geek;

import com.robust.tools.kit.io.FileTreeWalker;
import com.robust.tools.kit.io.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @Description:
 * @Author: robust
 * @CreateDate: 2019/8/27 10:32
 * @Version: 1.0
 */
public class DeletePdf {

    public static void deletePdf(Path path) {
        List<File> files = FileTreeWalker.listFileWithExtension(path.toFile(), "pdf");
        files.stream().forEach((f) -> {
            try {
                FileUtil.deleteFile(f);
                System.out.println("the file: " + f.getName() + " has delete");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        String location = "E:\\极客时间\\01-专栏课";
        Path path  = Paths.get(location);
        deletePdf(path);
    }
}
