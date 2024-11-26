package com.feng.netty.ddkk.endecoder.demo;

import io.netty.handler.codec.LineBasedFrameDecoder;

public class MyLineBasedFrameDecoder extends LineBasedFrameDecoder {

    private final static int MAX_LENGTH = 1024; // 帧的最大长度

    public MyLineBasedFrameDecoder() {
        super(MAX_LENGTH);
    }

}