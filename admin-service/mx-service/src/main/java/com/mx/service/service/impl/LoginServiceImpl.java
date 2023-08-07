package com.mx.service.service.impl;

import com.mx.service.constant.RedisKey;
import com.mx.service.entity.TUser;
import com.mx.service.enums.CommonErrorEnum;
import com.mx.service.mapper.TUserMapper;
import com.mx.service.service.LoginService;
import com.mx.service.utils.JwtUtils;
import com.mx.service.utils.MD5Utils;
import com.mx.service.utils.RedisUtils;
import com.mx.service.vo.Tokens;
import com.mx.service.vo.resp.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.ognl.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Description: 登录相关处理类
 * Author: mx
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private TUserMapper tUserMapper;
    //token过期时间
    private static final Integer TOKEN_EXPIRE_DAYS = 50000;
    //token续期时间
    private static final Integer TOKEN_RENEWAL_DAYS = 2;

    /**
     * 校验token是不是有效
     *
     * @param token
     * @return
     */
    @Override
    public boolean  verify(String token) {
        String uid = jwtUtils.getUidOrNull(token);
        if (Objects.isNull(uid)) {
            return false;
        }
        RedisUtils.set(RedisKey.getKey(RedisKey.USER_TOKEN_STRING, uid), token, TOKEN_EXPIRE_DAYS, TimeUnit.DAYS);

        String key = RedisKey.getKey(RedisKey.USER_TOKEN_STRING, uid);
        String realToken = RedisUtils.getStr(key);
        return Objects.equals(token, realToken);//有可能token失效了，需要校验是不是和最新token一致
    }

    @Async
    @Override
    public void renewalTokenIfNecessary(String token) {
        String uid = jwtUtils.getUidOrNull(token);
        if (Objects.isNull(uid)) {
            return;
        }
        String key = RedisKey.getKey(RedisKey.USER_TOKEN_STRING, uid);
        long expireDays = RedisUtils.getExpire(key, TimeUnit.DAYS);
        if (expireDays == -2) {//不存在的key
            return;
        }
        if (expireDays < TOKEN_RENEWAL_DAYS) {//小于一天的token帮忙续期
            RedisUtils.expire(key, TOKEN_EXPIRE_DAYS, TimeUnit.DAYS);
        }
    }

    @Override
    public String login(String uid) {
        String key = RedisKey.getKey(RedisKey.USER_TOKEN_STRING, uid);
        String token = RedisUtils.getStr(key);
        if (StringUtils.isNotBlank(token)) {
            return token;
        }
        //获取用户token
        token = jwtUtils.createToken(uid);
        RedisUtils.set(key, token, TOKEN_EXPIRE_DAYS, TimeUnit.DAYS);//token过期用redis中心化控制，初期采用5天过期，剩1天自动续期的方案。后续可以用双token实现
        return token;
    }

    @Override
    public ApiResult userLogin(TUser tUser) {
        // 校验密码是否正确
        TUser tUser1 = tUserMapper.selectByUsername(tUser);
        if (tUser1 != null) {
            final String password = tUser.getPassword();
            if (!MD5Utils.passwordIsTrue(password, tUser1.getPassword())) {
                return ApiResult.fail(CommonErrorEnum.LOGIN_ERROR);
            }
        }

        // 登录成功，获取token
        String token = login(tUser1.getId());
        ApiResult<Tokens> success = null;
        if (StringUtils.isNotBlank(token)) {
            Tokens token1 = new Tokens();
            token1.setToken(token);
             success = ApiResult.success(token1);
        }

        return success;
    }



    @Override
    public String getValidUid(String token) {
        boolean verify = verify(token);
        return verify ? jwtUtils.getUidOrNull(token) : null;
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
