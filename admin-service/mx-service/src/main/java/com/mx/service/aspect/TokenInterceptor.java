package com.mx.service.aspect;

import com.mx.service.constant.MDCKey;
import com.mx.service.enums.HttpErrorEnum;
import com.mx.service.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.Optional;

@Order(-2)
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHORIZATION_SCHEMA = "Bearer ";
    public static final String ATTRIBUTE_UID = "uid";

    @Autowired
    private LoginService loginService;
    @Value("${system.excluded.urls}")
    private String[] excludedUrls;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.匹配排除认证的，直接跳过
        String url = request.getRequestURI().replaceAll("/","");
        if (this.isMatch(url)) {
            if (log.isDebugEnabled()) {
                log.debug("====OMC拦截-已跳过认证[excluded url-{}]", url);
            }
            return true;
        }

        //获取用户登录token
//        String token = getToken(request);
//        String validUid = loginService.getValidUid(token);
//        if (Objects.nonNull(validUid)) {//有登录态
//            request.setAttribute(ATTRIBUTE_UID, validUid);
//        } else {
////            boolean isPublicURI = isPublicURI(request.getRequestURI());
////            if (!isPublicURI) {//又没有登录态，又不是公开路径，直接401
//                HttpErrorEnum.ACCESS_DENIED.sendHttpError(response);
//                return false;
////            }
//        }
//        MDC.put(MDCKey.UID, validUid);
        return true;
    }


    private boolean isMatch(String targetUrl) {
        if (ArrayUtils.isNotEmpty(excludedUrls)) {
            for (String configUrl : excludedUrls) {
                if (StringUtils.isNotBlank(targetUrl)
                        && StringUtils.isNotBlank(configUrl)
                        && targetUrl.endsWith(configUrl)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove(MDCKey.UID);
    }

    /**
     * 判断是不是公共方法，可以未登录访问的
     *
     * @param requestURI
     */
    private boolean isPublicURI(String requestURI) {
        String[] split = requestURI.split("/");
        return split.length > 2 && "public".equals(split[3]);
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER);
        return Optional.ofNullable(header)
                .filter(h -> h.startsWith(AUTHORIZATION_SCHEMA))
                .map(h -> h.substring(AUTHORIZATION_SCHEMA.length()))
                .orElse(null);
    }
}