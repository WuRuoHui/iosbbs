package com.wu.manager.utils;

/**
 * @program: iosbbs
 * @description: 自定义redis service
 * @author: Wu
 * @create: 2020-01-17 21:33
 **/

public interface StringRedisService {

    //存入键、值均为string
    public void setString(String key,String value);

    //存入键为string，值为object
    public void setObject(String key,Object o);

    //根据键取到值
    public String getString(String key);

    void putHash(String key, Object hk, Object hv);

    Object getHash(String key, Object hk);

    Long deleteHash(String key ,Object hk);
}
