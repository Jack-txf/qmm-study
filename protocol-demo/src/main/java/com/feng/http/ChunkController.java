package com.feng.http;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

/**
 * @version 1.0
 * @Author txf
 * @Date 2025/2/13 10:22
 * @注释 http chunk分块传输
 */
@RequestMapping("/http/chunk")
@RestController
public class ChunkController {

    @GetMapping(value = "/test1", produces = "text/event-stream")
    public StreamingResponseBody chunk1(){
        return null;
    }
}
