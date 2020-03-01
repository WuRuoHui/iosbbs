package com.wu.common.enums.impl;

import com.wu.common.enums.ICustomizeJieTypeCode;

/**
 * @program: iosbbs
 * @description: 自定义jie类型enum
 * @author: Wu
 * @create: 2020-03-01 19:30
 **/

public enum CustomizeJieTypeCode implements ICustomizeJieTypeCode {
    JIE_JIE(0),
    JIE_SHARE(99),
    JIE_DISCUSSION(100),
    JIE_ADVICE(101),
    JIE_NOTICE(168),
    JIE_CONDITION(169)
    ;

    private Integer code;

    @Override
    public Integer getCode() {
        return code;
    }

    CustomizeJieTypeCode(Integer code) {
        this.code = code;
    }
}
