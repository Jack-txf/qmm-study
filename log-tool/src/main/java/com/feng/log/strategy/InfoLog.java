package com.feng.log.strategy;

import com.feng.log.config.YmlConfig;
import com.feng.log.pojo.R;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InfoLog implements LogStrategy{
    @Override
    public R tackle(YmlConfig ymlConfig) {
        String path = ymlConfig.getLocation() + "\\" + ymlConfig.getName();
        ArrayList<String> resList = new ArrayList<>();
        List<Long> big = new ArrayList<>();

        try {
            FileReader reader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ( (line = bufferedReader.readLine()) != null ) {
                if ( line.contains("execute time")) {
                    int start = line.indexOf(":");
                    int end = line.lastIndexOf("m");
                    String s = line.substring(start+1, end);
                    long aLong = Long.parseLong(s.trim());
                    if ( aLong > 1000 ) big.add(aLong);
                    resList.add(line);
                }
            }

        } catch ( IOException e) {
            e.printStackTrace();
        }
        return R.success().setData("msg", "INFO")
                .setData("ana", resList).setData("big", big);
    }
}
