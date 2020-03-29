package com.lp.mall.passport.controller;

import com.alibaba.fastjson.JSON;
import com.lp.mall.util.HttpClientUtil;

import java.util.HashMap;
import java.util.Map;

public class TestOauth2 {

    public static String getCode(){

        // 1 获得授权码
        // 591303973
        // http://passport.gmall.com:8085/vlogin

        String s1 = HttpClientUtil.doGet("https://api.weibo.com/oauth2/authorize?client_id=591303973&response_type=code&redirect_uri=http://127.0.0.1:8085/vlogin");

        System.out.println(s1);

        // 在第一步和第二部返回回调地址之间,有一个用户操作授权的过程

        // 2 返回授权码到回调地址
        return null;
    }

    public static String getAccess_token(){
        // 3 换取access_token
        // client_secret=1a5da516aa468ac506cc3dab9b088cf2
        String s3 = "https://api.weibo.com/oauth2/access_token?";//?client_id=187638711&client_secret=a79777bba04ac70d973ee002d27ed58c&grant_type=authorization_code&redirect_uri=http://passport.gmall.com:8085/vlogin&code=CODE";
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("client_id","591303973");
        paramMap.put("client_secret","1a5da516aa468ac506cc3dab9b088cf2");
        paramMap.put("grant_type","authorization_code");
        paramMap.put("redirect_uri","http://127.0.0.1:8085/vlogin");
        paramMap.put("code","dd140f515955a95d855142ace23b93dc");// 授权有效期内可以使用，没新生成一次授权码，说明用户对第三方数据进行重启授权，之前的access_token和授权码全部过期
        String access_token_json = HttpClientUtil.doPost(s3, paramMap);

       Map<String,String> access_map = JSON.parseObject(access_token_json,Map.class);

       System.out.println(access_map.get("access_token"));
       System.out.println(access_map.get("uid"));

        return access_map.get("access_token");
    }

    public static Map<String,String> getUser_info(){

        // 4 用access_token查询用户信息
        String s4 = "https://api.weibo.com/2/users/show.json?access_token=2.00IhTwpF0rKDBefd17737dd00NTgad&uid=5348194470";
        String user_json = HttpClientUtil.doGet(s4);
        Map<String,String> user_map = JSON.parseObject(user_json,Map.class);
        System.out.println(user_map);
        System.out.println(user_map.get("1"));

        return user_map;
    }


    public static void main(String[] args) {

        getUser_info();

    }
}
