package com.mx.service.constant;

/**
 * @author mx
 */
public class RedisKey {
    private static final String BASE_KEY = "mallchat:";

    /**
     * 在线用户列表
     */
    public static final String ONLINE_UID_ZET = "online";

    /**
     * 离线用户列表
     */
    public static final String OFFLINE_UID_ZET = "offline";

    /**
     * 用户信息
     */
    public static final String USER_INFO_STRING = "userInfo:uid_%s";

    /**
     * 用户token存放
     */
    public static final String USER_TOKEN_STRING = "userToken:uid_%s";

    /**
     * 用户的信息更新时间
     */
    public static final String USER_MODIFY_STRING = "userModify:uid_%s";

    /**
     * 用户的信息汇总
     */
    public static final String USER_SUMMARY_STRING = "userSummary:uid_%s";

    /**
     * 用户GPT聊天次数
     */
    public static final String USER_CHAT_NUM = "useChatGPTNum:uid_%s";

    public static final String USER_CHAT_CONTEXT = "useChatGPTContext:uid_%s_roomId_%d";

    /**
     * 用户上次使用GLM使用时间
     */
    public static final String USER_GLM2_TIME_LAST = "userGLM2UseTime:uid_%s";

    public static String getKey(String key, Object... objects) {
        return BASE_KEY + String.format(key, objects);
    }

}
