package com.couldtec.webserver.apps.convert;

import java.util.HashMap;
import java.util.Map;

import com.couldtec.webserver.apps.entity.WSReqObj;
/**
 * 接口入参转换，webService入参 -》 restful入参(map)
 */
public class WSReqToMap {
	/**
	 * 业务下发回调翼翮接口入参转换，restful-》webService
	 * @param paramB
	 * @return resA
	 */
	public static Map<String,Object> ServiceDoneReturn(WSReqObj paramB)
	{
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("requ_id", paramB.getCmdID());
		map.put("CX_01", paramB.getCmdType());
		map.put("6", paramB.getClientType());
		map.put("ts", paramB.getDealDate());
		map.put("key_type", paramB.getParam().get("UserInfoType"));
		map.put("quer_key", paramB.getParam().get("UserInfo"));
		map.put("requ_id", paramB.getParam().get("RequestID"));
		map.put("result", paramB.getParam().get("Result"));
		return map;
	}
}
