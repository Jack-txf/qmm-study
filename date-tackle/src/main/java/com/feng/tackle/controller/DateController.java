package com.feng.tackle.controller;

import com.feng.tackle.entity.DatePojo;
import com.feng.tackle.service.DatePojoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/date")
public class DateController {
    @Resource
    private DatePojoService datePojoService;

    @GetMapping("/getTime/{id}")
    public Map<String, Object> getTime( @PathVariable("id") Integer id ) {
        DatePojo datePojo = datePojoService.getById(id);
        HashMap<String, Object> res = new HashMap<>();
        res.put("res", datePojo);
        return res;
    }

    @PostMapping("/insert")
    public Map<String, Object> insertTime(@RequestBody DatePojo datePojo) {
        datePojoService.save(datePojo);
        HashMap<String, Object> res = new HashMap<>();
        res.put("msg", "插入成功！");
        return res;
    }
}
