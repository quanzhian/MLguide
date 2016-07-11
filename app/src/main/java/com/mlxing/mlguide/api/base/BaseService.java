package com.mlxing.mlguide.api.base;

import com.mlxing.mlguide.api.common.ParamUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.Map;

/**
 * Created by quan on 2016/6/17.
 */
public class BaseService {


    /**
     * 主站 "http://124.173.70.55/";//
     */
    public static final String CML_API_DOMAIN = "http://www.mlxing.com";
    // public static final String CML_API_DOMAIN = "http://124.173.70.55";

    /**
     * 会员
     */
    public static final String CML_MEMBER_API_DOMAIN = "http://passport.mlxing.com";

    /**
     * 美丽社
     */
    public static final String CML_MLS_API_DOMAIN = "http://mls.mlxing.com/";
    // public static final String CML_MLS_API_DOMAIN =
    // "http://124.173.70.55:8092/";

    /**
     * 商城
     */
    public static final String CML_MALL_API_DOMAIN = "http://mall.mlxing.com";
    // public static final String CML_MALL_API_DOMAIN =
    // "http://124.173.70.55:8090/  ";

    /**
     * 微信
     */
    public static final String CML_WEIXIN_API_DOMAIN = "http://m.mlxing.com";

    /**
     * API接口返回成功标识码
     */
    public static final int API_RESPONSE_OK = 0;

    public static RequestCall post(String url, ParamUtil paramUtil){
       Map<String,String> map = paramUtil.getResultParamsMap();
        PostFormBuilder builder = OkHttpUtils.post().url(url);
        for (Map.Entry<String,String> entry : map.entrySet()) {
            builder.addParams(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }

}
