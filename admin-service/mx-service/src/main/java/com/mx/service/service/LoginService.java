package com.mx.service.service;

import com.mx.service.entity.TUser;
import com.mx.service.vo.resp.ApiResult;

/**
 * Description: 登录相关处理类
 * Author: mx
 */
public interface LoginService {


    /**
     * 校验token是不是有效
     *
     * @param token
     * @return
     */
    boolean verify(String token);

    /**
     * 刷新token有效期
     *
     * @param token
     */
    void renewalTokenIfNecessary(String token);

    /**
     * 登录成功，获取token
     *
     * @param uid
     * @return 返回token
     */
    String login(String uid);


    /**
     * 登录成功，获取token
     *
     * @return 返回token
     */
    ApiResult userLogin(TUser tUser);

    /**
     * 如果token有效，返回uid
     *
     * @param token
     * @return
     */
    String getValidUid(String token);

}
