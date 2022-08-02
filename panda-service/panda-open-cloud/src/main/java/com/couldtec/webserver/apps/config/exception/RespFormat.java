package com.couldtec.webserver.apps.config.exception;

import java.util.HashMap;
import java.util.Map;

import com.couldtec.webserver.apps.convert.WSRespToMap;
import com.couldtec.webserver.apps.entity.responsemodels.BaseResponseModel;
import com.couldtec.webserver.util.StringUtil;
import com.google.common.collect.Maps;
/**
 * 返回码定义类
 * @author jlp
 *
 */
public class RespFormat {
    private static Map<String,String>messageMap = Maps.newHashMap();
    
    public static class ProvNotAvilableException extends RuntimeException{
		private static final long serialVersionUID = 1L;
		ProvNotAvilableException(){
    		
    	}
    	public ProvNotAvilableException(String s){
    		super(s);
    	};
    }
    //初始化状态码与文字说明
    static {
		messageMap.put("200","适配层已成功处理了请求。");
		messageMap.put("201","POST/PUT操作已经提交" );
		messageMap.put("202","适配层已接受请求，但尚未处理。" );
		messageMap.put("203","适配层已成功处理了请求，但返回的信息可能来自另一来源。" );
		messageMap.put("204","适配层成功处理了请求，但没有返回任何内容。" );
		messageMap.put("205","适配层成功处理了请求，但没有返回任何内容。" );
		messageMap.put("206","适配层成功处理了部分 GET 请求。");
		messageMap.put("300","（多种选择）  针对请求，适配层可执行多种操作。适配层可根据请求者 (user agent) 选择一项操作，或提供操作列表供请求者选择。"); 
		messageMap.put("301","（永久移动）  请求的网页已永久移动到新位置。适配层返回此响应（对 GET 或 HEAD 请求的响应）时，会自动将请求者转到新位置。"); 
		messageMap.put("302","（临时移动）  适配层目前从不同位置的网页响应请求，但请求者应继续使用原有位置来进行以后的请求。" );
		messageMap.put("303","（查看其他位置） 请求者应当对不同的位置使用单独的 GET 请求来检索响应时，适配层返回此代码。" );
		messageMap.put("304","（未修改） 自从上次请求后，请求的网页未修改过。 适配层返回此响应时，不会返回网页内容。" );
		messageMap.put("305","（使用代理） 请求者只能使用代理访问请求的网页。如果适配层返回此响应，还表示请求者应使用代理。" );
		messageMap.put("307","（临时重定向）  适配层目前从不同位置的网页响应请求，但请求者应继续使用原有位置来进行以后的请求。");
		messageMap.put("400","（错误请求） 适配层不理解请求的语法。" );
		messageMap.put("401","（未授权） 请求要求身份验证。 对于需要登录的网页，适配层可能返回此响应。" );
		messageMap.put("403","（禁止） 适配层拒绝请求。" );
		messageMap.put("404","（未找到） 适配层找不到请求的网页。");
		messageMap.put("405","（方法禁用） 禁用请求中指定的方法。" );
		messageMap.put("406","（不接受） 无法使用请求的内容特性响应请求的网页。" );
		messageMap.put("407","（需要代理授权） 此状态代码与 401（未授权）类似，但指定请求者应当授权使用代理。" );
		messageMap.put("408","（请求超时）  适配层等候请求时发生超时。" );
		messageMap.put("409","（冲突）  适配层在完成请求时发生冲突。适配层必须在响应中包含有关冲突的信息。" );
		messageMap.put("410","（已删除）  如果请求的资源已永久删除，适配层就会返回此响应。" );
		messageMap.put("411","（需要有效长度） 适配层不接受不含有效内容长度标头字段的请求。" );
		messageMap.put("412","（未满足前提条件） 适配层未满足请求者在请求中设置的其中一个前提条件。" );
		messageMap.put("413","（请求实体过大） 适配层无法处理请求，因为请求实体过大，超出适配层的处理能力。" );
		messageMap.put("414","（请求的 URI 过长） 请求的 URI（通常为网址）过长，适配层无法处理。" );
		messageMap.put("415","（不支持的媒体类型） 请求的格式不受请求页面的支持。" );
		messageMap.put("416","（请求范围不符合要求） 如果页面无法提供请求的范围，则适配层会返回此状态代码。" );
		messageMap.put("417","（未满足期望值） 适配层未满足”期望”请求标头字段的要求。");
		messageMap.put("500","适配层遇到错误，无法完成请求。" );
		messageMap.put("501","（尚未实施） 适配层不具备完成请求的功能。例如，适配层无法识别请求方法时可能会返回此代码。" );
		messageMap.put("502","（错误网关） 适配层作为网关或代理，从上游适配层收到无效响应。" );
		messageMap.put("503","适配层调用itms+响应超时" );
		messageMap.put("504","适配层调用itms+响应超时" );
		messageMap.put("505","（HTTP 版本不受支持） 适配层不支持请求中所用的 HTTP 协议版本。");
		messageMap.put("100001", "目前该省份的北向接口本适配层还不能调用，尚未配置！");
		messageMap.put("100002", "开通ipsec失败");
    }
    
    public static BaseResponseModel retParam(int code, String details,String req_id) {
    	BaseResponseModel json = new BaseResponseModel(StringUtil.getStringValue(code),messageMap.get(String.valueOf(code))+"("+details+")",req_id);
    	json.setCode(WSRespToMap.codeTransMit(json.getCode()));
    	return json;
    }
    
    /**
    * 运行中遇到业务上的错误，返回错误码，正常返回请求
    * @param code
    * @param reson
    * @param cmdId
    * @return
    */
    public static Map<String,Object> errInfoReturn(String cmdId,String code,String reson){
    	Map<String,Object> resB=new HashMap<String,Object>();
		resB.put("requ_id", cmdId);
		resB.put("code", code);
		resB.put("reason", reson);
		return resB;
    }
    
}
