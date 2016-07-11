package com.mlxing.mlguide;

import com.mlxing.mlguide.api.ScenicService;
import com.mlxing.mlguide.api.base.BeanCallBack;
import com.mlxing.mlguide.api.base.ClientJsonResp;
import com.mlxing.mlguide.entity.Scene;
import com.zhy.http.okhttp.OkHttpUtils;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        //初始化okhttp
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .build();
        OkHttpUtils.initClient(okHttpClient);
        ScenicService.loadData("广州",null,null,1,10).execute(new BeanCallBack<ClientJsonResp<List<Scene>>>() {

            @Override
            public void onError(Call call, Exception e, int id) {
                assertEquals(4, 2 + 5);
            }

            @Override
            public void onResponse(ClientJsonResp<List<Scene>> response, int id) {
                System.out.print(response);
                assertEquals(4, 2 + 2);
            }
        });

    }
}