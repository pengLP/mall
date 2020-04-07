package com.lp.mall.passport.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.lp.mall.bean.UmsMember;
import com.lp.mall.service.CartService;
import com.lp.mall.service.UserService;
import com.lp.mall.util.CookieUtil;
import com.lp.mall.util.HttpClientUtil;
import com.lp.mall.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PassportController {

    @Reference
    UserService userService;

    @RequestMapping("vlogin")
    public String vlogin(String code , HttpServletRequest request) {

        System.out.println(code);
        String s3 = "https://api.weibo.com/oauth2/access_token?";
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("client_id","591303973");
        paramMap.put("client_secret","1a5da516aa468ac506cc3dab9b088cf2");
        paramMap.put("grant_type","authorization_code");
        paramMap.put("redirect_uri","http://127.0.0.1:8085/vlogin");
        paramMap.put("code",code);// 授权有效期内可以使用，没新生成一次授权码，说明用户对第三方数据进行重启授权，之前的access_token和授权码全部过期
        String access_token_json = HttpClientUtil.doPost(s3, paramMap);
        Map<String , Object> access_map = JSON.parseObject(access_token_json , Map.class);

        //access_token换取用户信息
        String uid = (String)access_map.get("uid");
        String access_token = (String)access_map.get("access_token");
        String show_user_url = "https://api.weibo.com/2/users/show.json?access_token="+access_token+"&uid="+uid;
        String user_json = HttpClientUtil.doGet(show_user_url);
        Map<String , Object> user_map = JSON.parseObject(user_json , Map.class);
        //将用户信息保存到数据库，用户类型设置为微博用户
        UmsMember umsMember = new UmsMember();
        umsMember.setSourceType("2");
        umsMember.setAccessCode(code);
        umsMember.setAccessToken(access_token);
        umsMember.setSourceUid((String) user_map.get("idstr"));
        umsMember.setCity((String)user_map.get("location"));
        umsMember.setNickname((String)user_map.get("screen_name"));
        System.out.println(umsMember);
        String gender = (String) user_map.get("gender");
        String g = "0";
        if (gender.equals("m")) {
            g = "1";
        }
        umsMember.setGender(g);
        UmsMember umsCheck = new UmsMember();
        umsCheck.setSourceUid(umsMember.getSourceUid());
        UmsMember umsMemberCheck = userService.checkOauthUser(umsCheck);
        if (umsMemberCheck == null) {
            userService.addOauthUser(umsMember);
        }else {
            umsMember = umsMemberCheck;
        }

        //生成jwt的token，并且重定向到首页，携带该token
        String token = null;
        String memberId = umsMember.getId();
        String nickName = umsMember.getNickname();
        Map<String,Object> userMap = new HashMap<>();
        userMap.put("memberId",memberId);
        userMap.put("nickname",nickName);

        String ip = request.getHeader("x-forwarded-for");// 通过nginx转发的客户端ip
        if(StringUtils.isBlank(ip)){
            ip = request.getRemoteAddr();// 从request中获取ip
            if(StringUtils.isBlank(ip)){
                ip = "127.0.0.1";
            }
        }
        // 按照设计的算法对参数进行加密后，生成token
        token = JwtUtil.encode("2020mall", userMap, ip);

        return "redirect:http://127.0.0.1:8083/index?token="+token;
    }


    @RequestMapping("verify")
    @ResponseBody
    public String verify(String token,String currentIp,HttpServletRequest request){


        // 通过jwt校验token真假
        Map<String,String> map = new HashMap<>();


        Map<String, Object> decode = JwtUtil.decode(token, "2020mall", currentIp);
        if(decode!=null){
            map.put("status","success");
            map.put("memberId",(String)decode.get("memberId"));
            map.put("nickname",(String)decode.get("nickname"));
        }else{
            map.put("status","fail");
        }
        System.out.println(map);

        return JSON.toJSONString(map);

    }


    @RequestMapping("login")
    @ResponseBody
    public String login(UmsMember umsMember, HttpServletRequest request){

        String token = "";

        // 调用用户服务验证用户名和密码
        UmsMember umsMemberLogin = userService.login(umsMember);

        if (umsMemberLogin != null) {
            //登陆成功
            // 用jwt制作token
            String memberId = umsMemberLogin.getId();
            String nickname = umsMemberLogin.getNickname();
            Map<String,Object> userMap = new HashMap<>();
            userMap.put("memberId",memberId);
            userMap.put("nickname",nickname);

            String ip = request.getHeader("x-forwarded-for");// 通过nginx转发的客户端ip
            if(StringUtils.isBlank(ip)){
                ip = request.getRemoteAddr();// 从request中获取ip
                if(StringUtils.isBlank(ip)){
                    ip = "127.0.0.1";
                }
            }
            // 按照设计的算法对参数进行加密后，生成token
            token = JwtUtil.encode("2020mall", userMap, ip);

            // 将token存入redis一份
            userService.addUserToken(token,memberId);
        }else {
            //登陆失败
            token = "fail";
        }
        return token;
    }



    @RequestMapping("index")
    public String index(String ReturnUrl, ModelMap map){

        map.put("ReturnUrl",ReturnUrl);
        return "index";
    }


    @RequestMapping("cookieTest")
    public String cookieTest(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.setCookie(request , response , "name" , "value" , 60*60 , true);
        return "cookieTest";
    }



}
