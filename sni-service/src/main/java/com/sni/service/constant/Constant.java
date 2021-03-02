package com.sni.service.constant;

public interface Constant {

    /**
     * 注册账号返回码
     */
    interface SignUpCode {

        int SUCCESS = 0;

        /**
         * 参数错误
         */
        int ARGS_ERROR = 1;

        /**
         * 已存在相同账号
         */
        int EXIST_ACCOUNT = 2;
    }

    /**
     * 数据状态
     */
    interface Status {

        /**
         * 正在使用
         */
        byte NOT_DISABLED = 0;

        /**
         * 禁止使用
         */
        byte IS_DISABLED = 1;
    }

    interface Num {
        int ONE = 1;
    }

    interface Time {
        long LOG_IN_EXPIRE_TIME = 30 * 60 * 1000;
    }
}
