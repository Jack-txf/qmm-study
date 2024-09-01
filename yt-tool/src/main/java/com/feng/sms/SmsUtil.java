package com.feng.sms;

import java.util.HashMap;
import java.util.Map;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;

public class SmsUtil {
    public static void sendMsg( Map<String, Object> paramMap) {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId("LTAI5tF8wQkgVZ2Ra3itXkVp")
                // 您的AccessKey Secret
                .setAccessKeySecret("VuWHH8dDiwMV6jAXB6N039UrtmqM1K");

        // 参数=====================
        String ordernum = (String) paramMap.get("orderMasterSn");
        String thcode = (String) paramMap.get("thcode");
        String phone = (String) paramMap.get("telephone");

        //获取客服电话
        // String storeId = (String) paramMap.get("storeId");
        String kfPhone = "023-63731818"; // 阅淘网客服电话
        config.endpoint = "dysmsapi.aliyuncs.com";

        try {
            Client client = new com.aliyun.dysmsapi20170525.Client(config);
            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    .setPhoneNumbers(phone)
                    .setSignName("阅淘网")
                    .setTemplateCode("SMS_276320250")
                    .setTemplateParam("{\"ordernum\":\"" + ordernum + "\",\"thcode\":\"" + thcode + "\",\"phonenum\":\"" + kfPhone + "\"}");
            // 复制代码运行请自行打印 API 的返回值
            SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
            Map<String, Object> resultMap = sendSmsResponse.getBody().toMap();
            String code = (String) resultMap.get("Code");
            String message = (String) resultMap.get("Message");

            if (code.equals("OK")) {
                System.err.println("发送短信成功 ！！！！！！！！！");
            } else {
                System.err.println("发送短信失败 " + message);
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("orderMasterSn", "123456test"); // 添加主订单号
        paramsMap.put("thcode", "123456"); // 添加提货码

        String telephone = "15972677462";
        paramsMap.put("telephone", telephone); // 添加手机号

        sendMsg(paramsMap);
    }
}
