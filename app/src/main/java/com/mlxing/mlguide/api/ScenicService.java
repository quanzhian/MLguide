package com.mlxing.mlguide.api;

import android.util.Log;

import com.mlxing.mlguide.api.base.BaseService;
import com.mlxing.mlguide.api.common.ParamUtil;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.request.RequestCall;

/**
 * Created by quan on 2016/6/17.
 */
public class ScenicService extends BaseService {

    /**
     * 根据条件读取数据
     * @param cityName
     * @param name
     * @param scenicThemeId
     * @param pageNumber
     * @param pageSize
     */
    public static RequestCall loadData(String cityName, String name, Integer scenicThemeId,
                                       Integer pageNumber, Integer pageSize) {
        String url = CML_API_DOMAIN + "/api/scene/getSceneList";
        ParamUtil paramUtil = ParamUtil.getNewInstance();
        paramUtil.addParam("pageNumber", pageNumber.toString())
                .addParam("pageSize", pageSize.toString())
                .addParam("cityName", cityName)
                .addParam("name", name)
                .addParam("themeId",scenicThemeId == null ? null : scenicThemeId.toString());
        return post(url,paramUtil);
    }
}
