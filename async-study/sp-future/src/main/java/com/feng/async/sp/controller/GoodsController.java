package com.feng.async.sp.controller;

import com.feng.async.sp.common.Result;
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
@RequestMapping("/goods")
public class GoodsController {
    @Resource
    private GoodsService goodsService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private Executor customThreadExecutor;

    @GetMapping("/get/{goodsId}")
    public Result getDetails(@PathVariable("goodsId") String goodsId) {
        Result result = new Result();
        // 方式1.同步 耗时大概两秒！！！！！！！！！！！！！！
        //String describeMsg = goodsService.getDescribeMsg(goodsId);
        //List<String> category = categoryService.getCategory(goodsId);
        //result.setDescMsg(describeMsg);
        //result.setCategory(category);
        //return result;

        // 方式2.异步
        CompletableFuture<String> descFuture = CompletableFuture
                .supplyAsync(()-> goodsService.getDescribeMsg(goodsId), customThreadExecutor)
                .exceptionally( e ->{
                    System.out.println("出现异常");
                    return null;
                });

        CompletableFuture<List<String>> categoryFuture = CompletableFuture
                .supplyAsync(() -> categoryService.getCategory(goodsId), customThreadExecutor)
                .exceptionally(e -> {
                    System.out.println("出现异常");
                    return null;
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
