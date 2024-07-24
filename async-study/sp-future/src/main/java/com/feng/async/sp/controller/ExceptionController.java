package com.feng.async.sp.controller;

import com.feng.async.sp.common.Result;
import com.feng.async.sp.common.exception.MyException;
import com.feng.async.sp.service.CategoryService;
import com.feng.async.sp.service.GoodsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RestController
@RequestMapping("/exception")
public class ExceptionController {
    @Resource
    private GoodsService goodsService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private Executor customThreadExecutor;

    // 空指针异常
    @GetMapping("/null")
    public Result nullFunction() {
        Object o = null;
        int i = o.hashCode();
        return new Result();
    }

    // 自定义异常
    @GetMapping("/get/{goodsId}")
    public Result getDetails(@PathVariable("goodsId") String goodsId) {
        Result result = new Result();
        //异步
        CompletableFuture<String> descFuture = CompletableFuture
                .supplyAsync(()-> goodsService.getDescribeMsg(goodsId), customThreadExecutor);

        CompletableFuture<List<String>> categoryFuture = CompletableFuture
                .supplyAsync(() -> categoryService.createMyException(goodsId), customThreadExecutor)
                .exceptionally( e -> {
                    throw new MyException(4444, "小伙子，这是我创造的异常");
                });
        // 再合并二者的结果，到result中去
        CompletableFuture<Object> resultFuture = descFuture.thenCombine(categoryFuture, (desc, categories) -> {
            result.setDescMsg(desc);
            result.setCategory(categories);
            return null;
        });
        // 等待完成
        resultFuture.join();

        return result;
    }

}
