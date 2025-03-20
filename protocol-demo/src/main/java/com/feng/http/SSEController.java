package com.feng.http;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 1.0
 * @Author txf
 * @Date 2025/3/20 10:07
 * @注释 sse
 */
@RequestMapping("/http/sse")
@RestController
@CrossOrigin
public class SSEController {
    private static final Map<String, SseEmitter> sseMap = new ConcurrentHashMap<>();

    @GetMapping("/createSSEconnect/{uid}")
    public SseEmitter createSSEconnect(@PathVariable("uid") String uid) {
        SseEmitter sseEmitter;
        if (!sseMap.containsKey(uid)) {
            sseEmitter = new SseEmitter(6000L);
            sseMap.put(uid, sseEmitter);
        }
        return sseMap.get(uid);
    }

}
