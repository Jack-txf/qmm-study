package com.feng.http;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version 1.0
 * @Author txf
 * @Date 2025/2/13 10:22
 * @注释 http chunk分块传输
 */
@RequestMapping("/http/chunk")
@RestController
@CrossOrigin
public class ChunkController {

    @GetMapping(value = "/test1", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public StreamingResponseBody chunk1(){
        return null;
    }

    // 1.模拟sse
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


    // 2. 模拟对话生成 ------------- 前端是normalDemo.html
    // 模拟对话生成逻辑
    private String generateResponse(String input) {
        // 简单模拟，实际应替换为真实的AI逻辑
        return "您说的: " + input;
    }
    @GetMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public void chat(@RequestParam("input") String input, HttpServletResponse response) {
        response.setContentType(MediaType.TEXT_EVENT_STREAM_VALUE);
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String fullResponse = generateResponse(input);
            for (int i = 0; i < fullResponse.length(); i += 10) {
                int end = Math.min(i + 10, fullResponse.length());
                String part = fullResponse.substring(i, end);
                out.write("data: " + part + "\n\n");
                out.flush();
                Thread.sleep(200);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    // WebFlux
    //@PostMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    //public Flux<String> chat(@RequestBody String input) {
    //    List<String> responseParts = new ArrayList<>();
    //    String fullResponse = generateResponse(input);
    //    AtomicInteger index = new AtomicInteger(0);
    //
    //    // 将完整响应拆分成部分，模拟流式输出
    //    for (int i = 0; i < fullResponse.length(); i += 10) {
    //        int end = Math.min(i + 10, fullResponse.length());
    //        responseParts.add(fullResponse.substring(i, end));
    //    }
    //
    //    return Flux.fromIterable(responseParts)
    //            .delayElements(Duration.ofMillis(200))
    //            .map(part -> "data: " + part + "\n\n")
    //            .subscribeOn(Schedulers.parallel());
    //}
}
