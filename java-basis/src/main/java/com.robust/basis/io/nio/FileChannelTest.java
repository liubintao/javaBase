package com.robust.basis.io.nio;

import com.robust.basis.io.UnsafeUtil;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @Description:
 * @Author: robust
 * @CreateDate: 2019/2/19 16:15
 * @Version: 1.0
 */
public class FileChannelTest {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("text.txt");
        Files.createFile(path);
        FileChannel fileChannel = new RandomAccessFile(path.toFile(), "rw").getChannel();
        // 写
        byte[] data = new byte[4096];
        long position = 1024L;
//指定 position 写入 4kb 的数据
        fileChannel.write(ByteBuffer.wrap(data), position);
//从当前文件指针的位置写入 4kb 的数据
        fileChannel.write(ByteBuffer.wrap(data));
// 读
        ByteBuffer buffer = ByteBuffer.allocate(4096);
//指定 position 读取 4kb 的数据
        fileChannel.read(buffer,position);
//从当前文件指针的位置读取 4kb 的数据
        fileChannel.read(buffer);

        UnsafeUtil.UNSAFE.putObject(buffer, 16 ,null);
    }
}
