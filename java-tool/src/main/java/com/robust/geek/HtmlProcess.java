package com.robust.geek;

import com.robust.tools.kit.base.ExceptionUtil;
import com.robust.tools.kit.base.Platforms;
import com.robust.tools.kit.io.FileTreeWalker;
import com.robust.tools.kit.io.FileUtil;
import com.robust.tools.kit.text.StringBuilderHolder;
import org.dom4j.DocumentException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Description:
 * @Author: robust
 * @CreateDate: 2019/8/23 8:32
 * @Version: 1.0
 */
public class HtmlProcess {

    private static final Pattern HREF = Pattern.compile("^/<base\\S$");
    private static final String SCRIPT = "<!--jq cdn--> <script src=\"https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js\"></script>  <!--播放音频-->  <script>$(function(){ var audio = document.getElementById(\"my_audio\"); $('._2Kms_g0F_0').click('click',function(){if(audio.paused){audio.play(); }else{audio.pause();}});});</script>";

    public static void process(Path path) throws IOException {
        List<File> files = FileTreeWalker.listFileWithExtension(path.toFile(), "html");
        files.stream().forEach((f) -> {
            single(f);
        });
    }

    private static void single(File file) {
        String fileName = FileUtil.getFileName(file.getName());
        String mp3 = fileName.replace(".html", ".mp3");
        System.out.println(fileName);

        List<String> content = null;
        StringBuilder sb = new StringBuilderHolder(512).get();
        try {
            content = FileUtil.toLines(file);
            content.stream().forEach((c) -> {
                c = deleteBaseHref(c);
                c = replaceText(c);
                c = replaceAudio(c, mp3);
                c = insertScript(c);
                sb.append(c).append(Platforms.LINE_SEPARATOR);
            });
            FileUtil.write(sb.toString(), file);
//            System.out.println(sb.toString());
        } catch (IOException e) {
            throw ExceptionUtil.unchecked(e);
        }

    }

    private static String deleteBaseHref(String content) {
        if (content.contains("<base href")) {
//            System.out.println(content);
            StringBuilder sb = StringBuilderHolder.getGlobal().append(content);
            sb.insert(content.indexOf('>') + 1, " -->");
            sb.insert(0, "<!-- ");
//            System.out.println(sb.toString());
            return sb.toString();
        }
        return content;
    }

    private static String insertScript(String content) {
        if (content.contains("</body>")) {
//            System.out.println(content);
            StringBuilder sb = StringBuilderHolder.getGlobal().append(content);
            sb.insert(content.indexOf("</body>") - 7, SCRIPT);
//            System.out.println(sb.toString());
            return sb.toString();
        }
        return content;
    }

    private static String replaceAudio(String content, String fileName) {
        if (content.contains("<audio ")) {
//            System.out.println(content);
            content = content.replace("（加微信：642945106 发送“赠送”领取赠送精品课程 发数字“2”获取众筹列表。）", "");
            StringBuilder sb = StringBuilderHolder.getGlobal();
            sb.append(content.substring(0, content.indexOf("<audio")));
            sb.append("<audio id=\"my_audio\" src=\"" + fileName + "\" controls>");
            sb.append(content.substring(content.indexOf("</audio")));
//            System.out.println(sb.toString());
            return sb.toString();
        }
        return content;
    }

    private static String replaceText(String content) {
        if (content.contains("防止")) {
//            System.out.println(content);
            content = content.replace("防止断更 请务必加首发微信：1716143665", "");
        }
        return content;
    }


    public static void main(String[] args) throws IOException, DocumentException {
        String location = "E:\\极客时间\\01-专栏课\\75-编辑训练营";
        Path path = Paths.get(location);
        process(path);
    }
}
