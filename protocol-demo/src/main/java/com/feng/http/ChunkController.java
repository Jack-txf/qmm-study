package com.feng.http;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version 1.0
 * @Author txf
 * @Date 2025/2/13 10:22
 * @注释 http chunk分块传输
 */
@RequestMapping("/http/chunk")
@RestController
public class ChunkController {

    @GetMapping(value = "/test1", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public StreamingResponseBody chunk1(){
        return null;
    }

    @GetMapping("/sse")
    public SseEmitter handleSse() {
        SseEmitter emitter = new SseEmitter(60_000L); // 设置超时时间
        AtomicInteger i = new AtomicInteger();

        new Thread(() -> {
            try {
                while ( i.getAndIncrement() < 5 ) {
                    emitter.send("实时数据：" + System.currentTimeMillis());
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        }).start();
        return emitter;
    }
}
