package com.mlxing.mlguide.api.common;

import com.mlxing.mlguide.utils.StringUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 前端参数处理 3DES算法
 * 
 * @author xieat
 * 
 */
public class TripleDESClientParamUtil {

    /**
     * 加密接口传�?参数
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public static final String Encrypt(Map<String, String> map) throws Exception {
        Set<Entry<String, String>> set = map.entrySet();
        Iterator<Entry<String, String>> iterator = set.iterator();
        StringBuffer sb = new StringBuffer();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            if (iterator.hasNext()) {
                sb.append("&");
            }
        }
        TripleDES tripleDes = new TripleDES();
        return tripleDes.Encrypt(sb.toString());
    }

    public static final String Encrypt(Map<String, String> map, String sKEY) throws Exception {
        Set<Entry<String, String>> set = map.entrySet();
        Iterator<Entry<String, String>> iterator = set.iterator();
        StringBuffer sb = new StringBuffer();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            if (iterator.hasNext()) {
                sb.append("&");
            }
        }
        TripleDES tripleDes = new TripleDES();
        if (sKEY != null && sKEY.trim().length() > 0) {
            tripleDes.setSKEY(sKEY);
        }
        return tripleDes.Encrypt(sb.toString());
    }

    /**
     * 解密接口传�?传输
     * 
     * @param data
     * @return
     * @throws Exception
     */
    public static final Map<String, String> Decrypt(String data) throws Exception {
        Map<String, String> map = new HashMap<String, String>(5);
        TripleDES tripleDes = new TripleDES();
        String d = tripleDes.Decrypt(data);
        // data = mdn=""&pwd=""&timestamp=""
        String[] strArr = d.split("&");
        if (strArr != null && strArr.length > 0) {
            for (String str: strArr) {
                String[] paramArr = str.split("=");
                if (paramArr.length == 2) {
                    map.put(paramArr[0], StringUtil.stringValue(paramArr[1], ""));
                } else {
                    map.put(paramArr[0], "");
                }

            }
        }
        return map;
    }

    public static final Map<String, String> Decrypt(String data, String sKEY) throws Exception {
        Map<String, String> map = new HashMap<String, String>(5);
        TripleDES tripleDes = new TripleDES();
        if (sKEY != null && sKEY.trim().length() > 0) {
            tripleDes.setSKEY(sKEY);
        }
        String d = tripleDes.Decrypt(data);
        // data = mdn=""&pwd=""&timestamp=""
        String[] strArr = d.split("&");
        if (strArr != null && strArr.length > 0) {
            for (String str: strArr) {
                String[] paramArr = str.split("=");
                if (paramArr.length == 2) {
                    map.put(paramArr[0], StringUtil.stringValue(paramArr[1], ""));
                } else {
                    map.put(paramArr[0], "");
                }

            }
        }
        return map;
    }
}
