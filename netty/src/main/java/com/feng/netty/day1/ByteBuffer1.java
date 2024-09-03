package com.feng.netty.day1;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

import static com.feng.netty.util.ByteBufferUtil.debugAll;

// 读出写进

@Slf4j
public class ByteBuffer1 {
    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = ByteBuffer1.class.getClassLoader();
        URL resources = classLoader.getResource("data.txt");

        try ( RandomAccessFile file = new RandomAccessFile(resources.getFile(), "rw")){
            FileChannel channel = file.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(10);
            do {
                // 向 buffer 写入
                int len = channel.read(buffer);
                log.debug("读到字节数：{}", len);
                if (len == -1) {
                    break;
                }
                // 切换 buffer 读模式
                buffer.flip();
                while(buffer.hasRemaining()) {
                    log.debug("{}", (char)buffer.get());
                }
                // 切换 buffer 写模式
                buffer.clear();
            } while (true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void commonFun() {
        ByteBuffer buffer = ByteBuffer.allocate(20);
        buffer.put((byte) 65);

        buffer.flip();

        while (buffer.hasRemaining()) {
            System.err.println( (char)buffer.get() );
        }
    }

    @Test
    public void test() {
        ByteBuffer buffer1 = StandardCharsets.UTF_8.encode("你好");
        ByteBuffer buffer2 = Charset.forName("utf-8").encode("你好");

        debugAll(buffer1);
        debugAll(buffer2);

        CharBuffer buffer3 = StandardCharsets.UTF_8.decode(buffer1);
        System.out.println(buffer3.getClass());
        System.out.println(buffer3.toString());
    }
}
