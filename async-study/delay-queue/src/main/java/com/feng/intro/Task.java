package com.feng.intro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task implements Delayed {
    private String name; // 任务名字
    private Date endTime; // 任务截止时间
    private Long lastTimes; // 这个任务剩余多少时间启动(单位：毫秒)
    private Integer type;  // 任务类型--仅做参考

    @Override
    public long getDelay(TimeUnit unit) {
        return endTime.getTime() - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        Task tar = (Task) o;
        long t = this.lastTimes - tar.getLastTimes();
        return t <= 0 ? -1 : 1;
    }
}
