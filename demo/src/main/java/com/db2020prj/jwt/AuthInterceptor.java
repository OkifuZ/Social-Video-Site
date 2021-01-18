package com.db2020prj.jwt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.db2020prj.service.UserService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.concurrent.SynchronousQueue;

public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Object object) throws InvalidTokenException {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        System.out.println(token);
//        for (String s : httpServletRequest.getInputStream().)
//            System.out.println(s);
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();


        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(NeedToken.class) ||
            method.isAnnotationPresent(OptionalToken.class) ||
            method.isAnnotationPresent(AsAdmin.class)) {
                if (null == token || token.equals("null")) {
                    if (method.isAnnotationPresent(OptionalToken.class)) {
//                        RequestWrapper requestWrapper = (RequestWrapper) httpServletRequest;
//                        switch (requestWrapper.getMethod()) {
//                            case "POST" : {
//                                String body = requestWrapper.getBody();
//                                System.out.println("Request Body: " + body);
//                                JSONObject params = JSONObject.parseObject(body);
//                                params.put("username", "");
//                                params.put("userId", "");
//                                params.put("nickname", "");
//                                params.put("isAdmin", "");
//                                params.put("token", "");
//                                requestWrapper.setBody(JSON.toJSONString(params));
//                                break;
//                            }
//                            case "DELETE" :
//                            case "GET" : {
//                                System.out.println("empty parameter set.");
//                                requestWrapper.setParam("username", "");
//                                requestWrapper.setParam("userId", "");
//                                requestWrapper.setParam("nickname", "");
//                                requestWrapper.setParam("isAdmin", "");
//                                requestWrapper.setParam("token", "");
//                                break;
//                            }
//                        }
                        return true;
                    }
                    throw new InvalidTokenException("No Token Found.", 300);
                }
                int userId;
                String username;
                String nickname;
                boolean isAdmin;
                try {
                    DecodedJWT decodedJWT = Token.verify(token);
                    userId = decodedJWT.getClaim("userId").asInt();
                    username = decodedJWT.getClaim("username").asString();
                    nickname = decodedJWT.getClaim("nickname").asString();
                    isAdmin = decodedJWT.getClaim("isAdmin").asBoolean();
                    if (method.isAnnotationPresent(AsAdmin.class) && !isAdmin) {
                        throw new InvalidTokenException("Must operate as an Administrator!", 500);
                    }
                } catch (InvalidTokenException e) {
                    if (method.isAnnotationPresent(OptionalToken.class)) {
//                        RequestWrapper requestWrapper = (RequestWrapper) httpServletRequest;
//                        switch (requestWrapper.getMethod()) {
//                            case "POST" : {
//                                String body = requestWrapper.getBody();
//                                System.out.println("Request Body: " + body);
//                                JSONObject params = JSONObject.parseObject(body);
//                                params.put("username", "");
//                                params.put("userId", "");
//                                params.put("nickname", "");
//                                params.put("isAdmin", "");
//                                params.put("token", "");
//                                requestWrapper.setBody(JSON.toJSONString(params));
//                                break;
//                            }
//                            case "DELETE" :
//                            case "GET" : {
//                                requestWrapper.setParam("username", "");
//                                requestWrapper.setParam("userId", "");
//                                requestWrapper.setParam("nickname", "");
//                                requestWrapper.setParam("isAdmin", "");
//                                requestWrapper.setParam("token", "");
//                                break;
//                            }
//                        }
                        return true;
                    }
                    throw e;
                }
//                System.out.println("Auth succeeded.");
//                httpServletRequest.setAttribute("userId", userId);
//                httpServletRequest.setAttribute("username", username);
//                httpServletRequest.setAttribute("nickname", nickname);
                RequestWrapper requestWrapper = (RequestWrapper) httpServletRequest;
                switch (requestWrapper.getMethod()) {

                    case "POST" : {
                        String body = requestWrapper.getBody();
                        System.out.println("Request Body: " + body);
                        JSONObject params = JSONObject.parseObject(body);
                        params.put("username", username);
                        params.put("userId", userId);
                        params.put("nickname", nickname);
                        params.put("isAdmin", isAdmin);
                        params.put("token", token);
                        requestWrapper.setBody(JSON.toJSONString(params));
                        break;
                    }
                    case "DELETE" :
                    case "GET" : {
                        requestWrapper.setParam("username", username);
                        requestWrapper.setParam("userId", userId);
                        requestWrapper.setParam("nickname", nickname);
                        requestWrapper.setParam("isAdmin", isAdmin);
                        requestWrapper.setParam("token", token);
                        break;
                    }
                }

                return true;

        }
        return true;
    }


}
