package com.feng.t1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Williams_Tian
 * @CreateDate 2024/7/30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer id;
    private String name;

    public String sing() {
        try {
            Thread.sleep(1000);
            return "唱";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer compute() {
        try {
            Thread.sleep(1000);
            return 5 + 9;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean res() {
        try {
            Thread.sleep(1000);
            return true;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Object haha() {
        try {
            Thread.sleep(1000);
            return new Student(1, "ikun");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
