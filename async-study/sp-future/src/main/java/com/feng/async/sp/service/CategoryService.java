package com.feng.async.sp.service;

import java.util.List;

public interface CategoryService {
    List<String> getCategory(String goodsId);

    List<String> createMyException(String goodsId);
}
