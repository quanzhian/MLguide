package com.mlxing.mlguide.api.common;

import android.util.Log;

import com.mlxing.mlguide.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 参数工具类
 * @author quan
 *
 */
public class ParamUtil {

	private Map<String, String> map;

	public static final String appId = "gzly";

	public static final String appMd5 = "044F198C803E6BD32C19205E4A7AF7B67CA4673E739B25CB";

	public static final String app3DES = "83C2522CF77FEA2A76D35E4CFDA167A1F843435BD9D59149";

	public static final String version = "1.0";

	private ParamUtil() {
		map = new HashMap<String, String>();
	}

	/**
	 * 获取一个新的实例
	 * 
	 * @return
	 */
	public static ParamUtil getNewInstance() {
		return new ParamUtil();
	}

	/**
	 * 添加参数和值
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public ParamUtil addParam(String name, String value ) {
		if(!StringUtil.empty(value)){
			map.put( name, value );
		}
		return this;
	}
	
	/**
	 * 设置参数Map集
	 * @param paramsMap
	 */
	public void setParamsMap(Map<String, String> paramsMap) {
		this.map = paramsMap;
	}

	/**
	 * 获取处理后的参数集Map
	 * 
	 * @return
	 */
	public Map<String, String> getResultParamsMap() {
		Map<String, String> paramsMap = new HashMap<String, String>();
		try {



			String data = TripleDESClientParamUtil.Encrypt( map, app3DES );
			String t = String.valueOf( System.currentTimeMillis() );
			String sign = MD5Util.getMD5String( appId + version + t + appMd5 );
			paramsMap.put( "appId", appId );
			paramsMap.put( "v", version );
			paramsMap.put( "data", data );
			paramsMap.put( "t", t );
			paramsMap.put( "sign", sign );
			return paramsMap;
		}
		catch( Exception e ) {
			Log.e( "paramUtil", e.getMessage() );
		}
		return null;
	}

	public String getParamsStrs(String url){
		StringBuilder stringBuilder = new StringBuilder();
		String _md5Url = MD5Util.getMD5String(url);
		stringBuilder.append(_md5Url);
		if(map.size() > 0){
			for (Map.Entry<String,String> entry: map.entrySet()) {
				stringBuilder.append(entry.getKey()).append("_").append(entry.getValue()).append("_");
			}
		}
		stringBuilder.append("key");
		return stringBuilder.toString();
	}
	
//	/**
//	 * 获取处理后的参数集Map
//	 * 
//	 * @return
//	 */
//	public Map<String, String> getResultNoMD5ParamsMap() {
//		Map<String, String> paramsMap = new HashMap<String, String>();
//		try {
//			String data = TripleDESClientParamUtil.Encrypt( map );
//			String t = String.valueOf( System.currentTimeMillis() );
//			paramsMap.put( "appId", appId );
//			paramsMap.put( "v", version );
//			paramsMap.put( "data", data );
//			paramsMap.put( "t", t );
//			return paramsMap;
//		}
//		catch( Exception e ) {
//			Log.e( "paramUtil", e.getMessage() );
//		}
//		return null;
//	}
	
}
