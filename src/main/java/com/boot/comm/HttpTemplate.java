package com.boot.comm;


import com.boot.utils.CharsetUtil;
import com.boot.utils.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * HTTP共通类
 * Created by yunfan on 2018/3/17.
 */
@Component
public class HttpTemplate {

    private static final Logger logger = LoggerFactory.getLogger(HttpTemplate.class);


    /**
     * 生成签名。
     */
    public static String makeSign(String uid, String key, String api, String argsStr) {
        return SignUtil.md5Lower("",new StringBuffer().append("uid=").append(uid).append("&api=").append(api).append("&args=").append(argsStr).append("&key=").append(key).toString());
    }

    /**
     * 生成URL。
     */
    public static String makeUrl(String url, String uid, String key, String api, String argsStr) {
        return new StringBuffer().append(url).append("?uid=").append(uid).append("&api=").append(api).append("&args=").append(CharsetUtil.encodeURL(argsStr)).append("&sign=").append(makeSign(uid, key, api, argsStr)).toString();
    }

//    public static ResponseMessage doYoushuGet(String api, Map<String, Object> args) {
//        ResponseMessage response = new ResponseMessage();
//        try {
//            String argsStr = JsonUtil.toJsonString(args);// 参数转化
//            String str = makeYoushuUrl(api, argsStr);
//            System.out.println(str);
//            String res = new HttpUtil().doGet(str, 205000);// 发送请求
//            // System.out.println("api=" + api + "；response=" + res);
//            if (res == null) {
//                response.setResponseCode(ResponseCode.SUCCESS);
//                return response;
//            }
//            response = JsonUtil.toBeanFromStr(res, ResponseMessage.class);
//            if (response == null) {
//                response = new ResponseMessage();
//                response.setResponseCode(ResponseCode.SEARCH_FAILED);
//            }
//        } catch (Exception e) {
//            logger.error("查询有数接口失败：api={}，error={}", api, e.getMessage(), e);
//            response = new ResponseMessage();
//            response.setResponseCode(ResponseCode.SYSTEM_ERROR);
//        }
//
//        return response;
//    }
}
