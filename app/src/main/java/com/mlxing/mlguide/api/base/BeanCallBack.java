package com.mlxing.mlguide.api.base;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.callback.Callback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * http请求接收的数据转换成指定对象
 * Created by quan on 2016/6/18.
 */
public abstract class BeanCallBack<T> extends Callback<T> {

    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
        String resStr = response.body().string();
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            //如果用户写了泛型，就会进入这里，否者不会执行
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type beanType = parameterizedType.getActualTypeArguments()[0];
            if (beanType == String.class) {
                //如果是String类型，直接返回字符串
                return (T) resStr;
            } else {
                //如果是 Bean List Map ，则解析完后返回
                T t = JSON.parseObject(resStr, beanType);
                if (t instanceof ClientJsonResp) {
                    ClientJsonResp cls = (ClientJsonResp) t;
                    if (cls == null || cls.getCode() != BaseService.API_RESPONSE_OK) {
                        throw new Exception(cls.getMsg());
                    }
                }
                return t;
            }
        } else {
            //如果没有写泛型，直接返回Response对象
            return (T) response;
        }
    }

}
