package com.wu.manager.enums;

/**
 * @program: iosbbs
 * @description: 自定义错误码与提示信息
 * @author: Wu
 * @create: 2020-02-04 09:25
 **/

public enum CustomizeErrorCode implements ICustomizeErrorCode{

    GAME_NOT_FOUND(2001,"对应的游戏信息不存在，刷新试试！"),
    DELETE_DATA_FAIL(2002,"删除失败！"),
    DELETE_DATA_SUCCESS(2003,"删除成功"),
    NOT_ROW_SELECT(2004,"删除失败，请确认是否有选中行数据"),
    UPDATE_DATA_SUCCESS(2005,"更新成功"),
    UPDATE_DATA_FAIL(2006,"更新失败"),
    GAME_ALREADY_EXIST(2007,"游戏已存在，不可重复添加"),
    ;

    private Integer code;
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }}
