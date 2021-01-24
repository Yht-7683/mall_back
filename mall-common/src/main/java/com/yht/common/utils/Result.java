package com.yht.common.utils;



import com.yht.common.exception.ExcepCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yht
 */
public class Result extends HashMap<String, Object> {
    public Result() {
        put("code", 0);
        put("msg", "success");
    }
    public static Result error(int code,String msg){
        Result result = new Result();
        result.put("code", code);
        result.put("msg", msg);
        return result;
    }
    public static Result error(String msg){
        Result result = new Result();
        result.put("code", ExcepCode.INTERNAL_SERVER_ERROR);
        result.put("msg", msg);
        return result;
    }
    public static Result error(){
        Result result = new Result();
        result.put("code", ExcepCode.INTERNAL_SERVER_ERROR);
        result.put("msg", "服务器内部错误");
        return result;
    }
    public static Result ok(String msg) {
        Result r = new Result();
        r.put("code",ExcepCode.OK);
        r.put("msg", msg);
        return r;
    }
    public static Result ok(Map<String, Object> map) {
        Result r = new Result();
        r.putAll(map);
        return r;
    }

    public static Result ok() {
        return new Result();
    }
    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
