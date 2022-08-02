package com.couldtec.webserver.apps.convert;

import java.util.HashMap;
import java.util.Map;

import com.couldtec.webserver.apps.entity.WSRespObj;
import com.couldtec.webserver.util.StringUtil;
import com.alibaba.fastjson.JSONArray;

/**
 * 接口回参转换，webService回参 -》 restful回参(map)
 */
public class WSRespToMap
{
	/**
	 * 三个基本的错误码
	 * @author jlp
	 *
	 */
	public static final class RespCode{
		/**
		 * 正确，成功
		 */
		public static String SUCCESS="020000";
		/**
		 * 超时
		 */
		public static String TIMEOUT="021000";
		/**
		 * 未知异常
		 */
		public static String OTHERERR="023999";
	}
	/**
	 * 把itms返回的结果码转换成翼翮需要的
	 * 翼翮需要的是：
		亚信（02）	020000	成功
		亚信（02）	021000	响应超时
		亚信（02）	022000	入参格式错误
		亚信（02）	023000	无此业务类型
		亚信（02）	023001	无此操作类型
		亚信（02）	023002	宽带账号不合法
		亚信（02）	023003	查询到对应多个用户
		亚信（02）	023004	宽带账号查询不到对应用户
		亚信（02）	023005	宽带账号查询不到对应网关
		亚信（02）	023006	网关不在线
		亚信（02）	023007	未开通IPSecVPN业务
		亚信（02）	023008	未开通宽带业务
		亚信（02）	023009	等待网关上线后业务下发
		亚信（02）	023010	网关无响应
		亚信（02）	023011	桥接工作模式，无业务ip
		亚信（02）	023012	网关不支持
		亚信（02）	023013	服务忙
		亚信（02）	023999	其他
		itms定义的是：
		成功	0	操作结果成功
		失败	-1	
		无此业务类型	1	　
		无此操作类型	2	　
		入参格式错误	3	　
		宽带账号不合法	4	为空或者非字符串或者格式错误
		查询到对应多个用户	5	根据宽带账号查询出两个及以上Loid
		宽带账号查询不到对应用户	6	　
		宽带账号查询不到对应网关	7	　
		网关不在线	8	　
		未开通IPSecVPN业务	9	　
		未开通宽带业务	10	　
		等待网关上线后业务下发	11	当时业务下发时网关不在线，需要等到网关下次上线时在进行业务下发
		网关无响应	12	连接网关时无响应
		桥接工作模式，无业务ip	13	桥接的工作模式没有相关业务ip地址
		网关不支持	14	设备不支持业务开通
	 * 
	 * @param code
	 * @return
	 */
	public static String codeTransMit(String code){
		if("0".equals(code)||"200".equals(code)){//成功
			return RespCode.SUCCESS;
		}else if("1".equals(code)){//无此业务类型
			return "023000";
		}else if("2".equals(code)){//无此操作类型
			return "023001";
		}else if("3".equals(code)){//入参格式错误
			return "022000";
		}else if("4".equals(code)){//宽带账号不合法
			return "023002";
		}else if("5".equals(code)){//查询到对应多个用户
			return "023003";
		}else if("6".equals(code)){//宽带账号查询不到对应用户
			return "023004";
		}else if("7".equals(code)){//宽带账号查询不到对应网关
			return "023005";
		}else if("8".equals(code)){//网关不在线
			return "023006";
		}else if("9".equals(code)){//未开通IPSecVPN业务
			return "023007";
		}else if("10".equals(code)){//未开通宽带业务
			return "023008";
		}else if("11".equals(code)){//等待网关上线后业务下发
			return "023009";
		}else if("12".equals(code)){//网关无响应
			return "023010";
		}else if("13".equals(code)){//桥接工作模式，无业务ip
			return "023011";
		}else if("14".equals(code)){//网关不支持
			return "023012";
		}else if("503".equals(code)||"504".equals(code)){//响应超时
			return RespCode.TIMEOUT;
		}else {//其他错误
			return RespCode.OTHERERR;
		}
	}
	
	
	/**
	 * 回参中的最基本的参数
	 * @param resB
	 * @param respA
	 */
	public static void putBaseInfo(Map<String,Object> resB,WSRespObj respA){
		resB.put("requ_id", GetStrValue(respA.getCmdID()));
		resB.put("code", codeTransMit(respA.getRstCode()));
		resB.put("reason", GetStrValue(respA.getRstMsg()));
	}
	
	/**
	 * 网关信息查询接口回参转换
	 * @param respA
	 * @return resB
	 */
	public static Map<String,Object> QueryGetWay(WSRespObj respA)
	{
		Map<String,Object> resB=new HashMap<String,Object>();
		putBaseInfo(resB,respA);
		
		String mode=GetStrValue(respA.getParamMap().get("WanType"));
		String acc_mode="0";
		if("1".equals(mode)||"2".equals(mode)){
			//acc_mode="0";
		}else{
			acc_mode="1";
		}
		
		//上网方式:1桥接，2路由
		if("1".equals(mode)){
			mode="Bridge";
		}else if("2".equals(mode)){
			mode="Router";
		}else if("3".equals(mode)){
			mode="StaticIp";
		}else{
			mode="Unknown";
		}
		Map<String,Object> gw_infoMap=new HashMap<String,Object>();
		gw_infoMap.put("dev_sn", GetStrValue(respA.getParamMap().get("DeviceSN")));
		gw_infoMap.put("user_count", GetStrValue(respA.getParamMap().get("UserInfo")));
		gw_infoMap.put("loid", GetStrValue(respA.getParamMap().get("Loid")));
		gw_infoMap.put("loid_prev", GetStrValue(respA.getParamMap().get("LoidPrev")));
		gw_infoMap.put("status", GetStrValue(StringUtil.getIntegerValue(respA.getParamMap().get("Status"))));
		gw_infoMap.put("mode",mode);
		gw_infoMap.put("model", GetStrValue(respA.getParamMap().get("DeviceModel")));
		gw_infoMap.put("sw_version", GetStrValue(respA.getParamMap().get("Softwareversion")));
		gw_infoMap.put("hw_version", GetStrValue(respA.getParamMap().get("Hardwareversion")));
		gw_infoMap.put("manufacturer", GetStrValue(respA.getParamMap().get("DeviceVendor")));
		gw_infoMap.put("acce_mode", acc_mode);
		gw_infoMap.put("ip_addr", GetStrValue(respA.getParamMap().get("IpAddr")));
		gw_infoMap.put("online", GetStrValue(respA.getParamMap().get("Online")));
		gw_infoMap.put("ipsec_status", GetStrValue(respA.getParamMap().get("IpsecStatus")));
		gw_infoMap.put("has_net", GetStrValue(respA.getParamMap().get("IsNet")));
		resB.put("gw_info",gw_infoMap);
		return resB;
	}
	
	/**
	 * 网关业务ip地址查询接口回参转换
	 * @param respA
	 * @return resB
	 */
	public static Map<String,Object> QueryServIpAddr(WSRespObj respA)
	{
		Map<String,Object> resB=new HashMap<String,Object>();
		putBaseInfo(resB,respA);
		resB.put("serv_ipaddr", GetStrValue(respA.getParamMap().get("ServIpAddr")));
		resB.put("loid", GetStrValue(respA.getParamMap().get("Loid")));
		resB.put("loid_prev", GetStrValue(respA.getParamMap().get("LoidPrev")));
		
		return resB;
	}
	
	/**
	 * 网关IPSec状态查询接口回参转换
	 * @param respA
	 * @return resB
	 */
	public static Map<String,Object> QueryVpnStats(WSRespObj respA)
	{
		Map<String,Object> resB=new HashMap<String,Object>();
		putBaseInfo(resB,respA);
		resB.put("loid", GetStrValue(respA.getParamMap().get("Loid")));
		resB.put("loid_prev", GetStrValue(respA.getParamMap().get("LoidPrev")));
		String ipsecvpn_status="";
		if(!StringUtil.IsEmpty(GetStrValue(respA.getParamMap().get("IpsecVpnStatus")))){
			ipsecvpn_status=GetStrValue(respA.getParamMap().get("IpsecVpnStatus"));
		}else if(!StringUtil.IsEmpty(GetStrValue(respA.getParamMap().get("IpescVpnStatus")))){
			ipsecvpn_status=GetStrValue(respA.getParamMap().get("IpescVpnStatus"));
		}else if(!StringUtil.IsEmpty(GetStrValue(respA.getParamMap().get("OnlineStatus")))){
			ipsecvpn_status=GetStrValue(respA.getParamMap().get("OnlineStatus"));
		}
		resB.put("ipsecvpn_status",ipsecvpn_status);
		return resB;
	}
	
	/**
	 * 网关在线状态查询接口回参转换
	 * @param respA
	 * @return resB
	 */
	public static Map<String,Object> QueryDevOnline(WSRespObj respA)
	{
		Map<String,Object> resB=new HashMap<String,Object>();
		putBaseInfo(resB,respA);
		resB.put("loid", GetStrValue(respA.getParamMap().get("Loid")));
		resB.put("loid_prev", GetStrValue(respA.getParamMap().get("LoidPrev")));
		resB.put("online_status", GetStrValue(respA.getParamMap().get("OnlineStatus")));
		return resB;
	}
	
	/**
	 * Ping诊断接口回参转换
	 * @param respA
	 * @return resB
	 */
	public static Map<String,Object> PingDiagnostic(WSRespObj respA)
	{
		Map<String,Object> resB=new HashMap<String,Object>();
		putBaseInfo(resB,respA);
		resB.put("loid", GetStrValue(respA.getParamMap().get("Loid")));
		resB.put("loid_prev", GetStrValue(respA.getParamMap().get("LoidPrev")));
		Map<String,Object> ping_infoMap=new HashMap<String,Object>();
		ping_infoMap.put("dev_sn", GetStrValue(respA.getParamMap().get("DevSn")));
		ping_infoMap.put("success_num", GetStrValue(respA.getParamMap().get("SuccesNum")));
		ping_infoMap.put("fail_num", GetStrValue(respA.getParamMap().get("FailNum")));
		ping_infoMap.put("avgresponse_time", GetStrValue(respA.getParamMap().get("AvgResponseTime")));
		ping_infoMap.put("minresponse_time", GetStrValue(respA.getParamMap().get("MinResponseTime")));
		ping_infoMap.put("maxresponse_time", GetStrValue(respA.getParamMap().get("MaxResponseTime")));
		ping_infoMap.put("packetloss_rate", GetStrValue(respA.getParamMap().get("PacketLossRate")));
		ping_infoMap.put("ipordomain_name", GetStrValue(respA.getParamMap().get("IPOrDomainName")));
		resB.put("ping_info", ping_infoMap);
		return resB;
	}
	
	/**
	 * 业务下发结果查询接口回参转换
	 * @param respA
	 * @return resB
	 */
	public static Map<String,Object> QueryBssStats(WSRespObj respA)
	{
		Map<String,Object> resB=new HashMap<String,Object>();
		putBaseInfo(resB,respA);
		resB.put("bss_stats", GetStrValue(respA.getParamMap().get("BssStats")));
		resB.put("loid", GetStrValue(respA.getParamMap().get("Loid")));
		resB.put("loid_prev", GetStrValue(respA.getParamMap().get("LoidPrev")));
		
		return resB;
	}
	
	/**
	 * 业务下发接口回参转换
	 * @param respA
	 * @return resB
	 */
	public static Map<String,Object> ServiceDone(WSRespObj respA)
	{
		Map<String,Object> resB=new HashMap<String,Object>();
		putBaseInfo(resB,respA);
		resB.put("servicedone_stats", GetStrValue(respA.getParamMap().get("ServiceDoneStats")));
		resB.put("loid", GetStrValue(respA.getParamMap().get("Loid")));
		resB.put("loid_prev", GetStrValue(respA.getParamMap().get("LoidPrev")));
		return resB;
	}
	
	/**
	 * 网关重启接口回参转换
	 * @param respA
	 * @return resB
	 */
	public static Map<String,Object> GetWayReboot(WSRespObj respA)
	{
		Map<String,Object> resB=new HashMap<String,Object>();
		putBaseInfo(resB,respA);
		resB.put("reboot_stats", GetStrValue(respA.getParamMap().get("RebootStats")));
		resB.put("loid", GetStrValue(respA.getParamMap().get("Loid")));
		resB.put("loid_prev", GetStrValue(respA.getParamMap().get("LoidPrev")));
		return resB;
	}
	
	/**
	 * IPSecVPN配置参数查询接口回参转换
	 * @param respA
	 * @return resB
 * 				requ_id	String	Y	唯一标识请求的ID值，全局唯一
				code	Int	Y	接口调用结果返回码，参照3.1
				reason	String	N	结果描述
				loid	String	N	网关最新的loid。正常返回时一定会有loid
				loid_prev	String	N	异常情况下可能会有一个宽带帐号对应两个loid的情况。此时，在loid参数返回当前宽带帐号绑定的最新loid，而loid_prev返回绑定之前的loid。
				config_info	config_info_struct	N	网关IPSecVPN配置参数信息。为Jason描述的复合类型
													定义见后面描述

	 * 			config_info_struct:
	 * 				ipsec_type	String	Y	IPSec 类型
					remote_ip	Int	Y	Site-to-Site 模式下对端IP 地址。
					remote_subnet	Int		对端子网
					local_subnet	Int		本地子网
					exchange_mode	Int		IKE 协商方式
					ike_auth_algorithm	Int		IKE认证算法
					ike_auth__method	String		IKE 验证方法
					ike_encryption	String		IKE 加密算法
					ike_dhgroupp			IKE DH 组
					ike_idtype			IKE 身份类型
					ike_localname			IKE 本端名称
					ike_remotename			IKE 对端名称
					ike_presharekey			IKE 预共享密钥
					ipsec_transform			IPSec 安全协议
					esp_auth_algorithem			IPsec 验证算法
					esp_encrypt_algorithm			IPsec 加密算法
					ipsec_pfs			IPSec DH 组
					ike_saperiod			设置IKE SA 生命周期
					ipsec_satime_period			设置IPsec SA 时间生命周期
					ipsec_satraffic_period			设置IPsec SA 流量生命周期
					ah_auth_algorithm			AH 验证算法

	 */
	public static Map<String,Object> QueryConfigure(WSRespObj respA)
	{
		Map<String,Object> resB=new HashMap<String,Object>();
		putBaseInfo(resB,respA);
		resB.put("loid", GetStrValue(respA.getParamMap().get("Loid")));
		resB.put("loid_prev", GetStrValue(respA.getParamMap().get("LoidPrev")));
		Map<String,Object> config_infoMap=new HashMap<String,Object>();
		config_infoMap.put("ipsec_type", GetStrValue(respA.getParamMap().get("IPSecType")));
		config_infoMap.put("remote_ip",GetStrValue(respA.getParamMap().get("RemoteIP")));
		config_infoMap.put("remote_subnet",GetStrValue(respA.getParamMap().get("RemoteSubnet")));
		config_infoMap.put("local_subnet",GetStrValue(respA.getParamMap().get("LocalSubnet")));
		config_infoMap.put("remote_domain",GetStrValue(respA.getParamMap().get("RemoteDomain")));
		config_infoMap.put("ip_secout_interface",GetStrValue(respA.getParamMap().get("IPSecOutInterface")));
		config_infoMap.put("ip_secencapsulation_mode",GetStrValue(respA.getParamMap().get("IPSecEncapsulationMode")));
		config_infoMap.put("exchange_mode",GetStrValue(respA.getParamMap().get("ExchangeMode")) );
		config_infoMap.put("ike_auth_algorithm",GetStrValue(respA.getParamMap().get("IKEAuthenticationAlgorithm") ));
		config_infoMap.put("ike_auth__method", GetStrValue(respA.getParamMap().get("IKEAuthenticationMethod")));
		config_infoMap.put("ike_encryption", GetStrValue(respA.getParamMap().get("IKEEncryptionAlgorithm")));
		config_infoMap.put("ike_dhgroupp", GetStrValue(respA.getParamMap().get("IKEDHGroup")));
		config_infoMap.put("ike_idtype", GetStrValue(respA.getParamMap().get("IKEIDType")));
		config_infoMap.put("ike_localname", GetStrValue(respA.getParamMap().get("IKELocalName")));
		config_infoMap.put("ike_remotename", GetStrValue(respA.getParamMap().get("IKERemoteName")));
		config_infoMap.put("ike_presharekey", GetStrValue(respA.getParamMap().get("IKEPreshareKey")));
		config_infoMap.put("ipsec_transform", GetStrValue(respA.getParamMap().get("IPSecTransform")));
		config_infoMap.put("esp_auth_algorithem", GetStrValue(respA.getParamMap().get("ESPAuthenticationAlgorithm")));
		config_infoMap.put("esp_encrypt_algorithm", GetStrValue(respA.getParamMap().get("ESPEncryptionAlgorithm")));
		config_infoMap.put("ipsec_pfs", GetStrValue(respA.getParamMap().get("IPSecPFS")));
		config_infoMap.put("ike_saperiod", GetStrValue(respA.getParamMap().get("IKESAPeriod")));
		config_infoMap.put("ipsec_satime_period", GetStrValue(respA.getParamMap().get("IPSecSATimePeriod")));
		config_infoMap.put("ipsec_satraffic_period", GetStrValue(respA.getParamMap().get("IPSecSATrafficPeriod")));
		config_infoMap.put("ah_auth_algorithm", GetStrValue(respA.getParamMap().get("AHAuthenticationAlgorithm")));
		config_infoMap.put("dpd_enable", GetStrValue(respA.getParamMap().get("DPDEnable")));
		config_infoMap.put("dpd_threshold", GetStrValue(respA.getParamMap().get("DPDThreshold")));
		config_infoMap.put("dpd_retry", GetStrValue(respA.getParamMap().get("DPDRetry")));
		resB.put("config_info", config_infoMap);
		return resB;
	}
	
	
	public static Map<String,Object> queryConfigureFromDb(Map<String,Object> respA)
	{
		Map<String,Object> resB=new HashMap<String,Object>();
		resB.put("requ_id", GetStrValue(respA.get("request_id")));
		resB.put("code", GetStrValue(respA.get("code")));
		resB.put("reason", GetStrValue(respA.get("reason")));
		Map<String,Object> config_infoMap=new HashMap<String,Object>();
		config_infoMap.put("ipsec_type", GetStrValue(respA.get("IPSecType")));
		config_infoMap.put("remote_ip",GetStrValue(respA.get("RemoteIP")));
		config_infoMap.put("remote_subnet",GetStrValue(respA.get("RemoteSubnet")));
		config_infoMap.put("local_subnet",GetStrValue(respA.get("LocalSubnet")));
		config_infoMap.put("remote_domain",GetStrValue(respA.get("RemoteDomain")));
		config_infoMap.put("ip_secout_interface",GetStrValue(respA.get("IPSecOutInterface")));
		config_infoMap.put("ip_secencapsulation_mode",GetStrValue(respA.get("IPSecEncapsulationMode")));
		config_infoMap.put("exchange_mode",GetStrValue(respA.get("ExchangeMode")) );
		config_infoMap.put("ike_auth_algorithm",GetStrValue(respA.get("IKEAuthenticationAlgorithm") ));
		config_infoMap.put("ike_auth__method", GetStrValue(respA.get("IKEAuthenticationMethod")));
		config_infoMap.put("ike_encryption", GetStrValue(respA.get("IKEEncryptionAlgorithm")));
		config_infoMap.put("ike_dhgroupp", GetStrValue(respA.get("IKEDHGroup")));
		config_infoMap.put("ike_idtype", GetStrValue(respA.get("IKEIDType")));
		config_infoMap.put("ike_localname", GetStrValue(respA.get("IKELocalName")));
		config_infoMap.put("ike_remotename", GetStrValue(respA.get("IKERemoteName")));
		config_infoMap.put("ike_presharekey", GetStrValue(respA.get("IKEPreshareKey")));
		config_infoMap.put("ipsec_transform", GetStrValue(respA.get("IPSecTransform")));
		config_infoMap.put("esp_auth_algorithem", GetStrValue(respA.get("ESPAuthenticationAlgorithm")));
		config_infoMap.put("esp_encrypt_algorithm", GetStrValue(respA.get("ESPEncryptionAlgorithm")));
		config_infoMap.put("ipsec_pfs", GetStrValue(respA.get("IPSecPFS")));
		config_infoMap.put("ike_saperiod", GetStrValue(respA.get("IKESAPeriod")));
		config_infoMap.put("ipsec_satime_period", GetStrValue(respA.get("IPSecSATimePeriod")));
		config_infoMap.put("ipsec_satraffic_period", GetStrValue(respA.get("IPSecSATrafficPeriod")));
		config_infoMap.put("ah_auth_algorithm", GetStrValue(respA.get("AHAuthenticationAlgorithm")));
		config_infoMap.put("dpd_enable", GetStrValue(respA.get("DPDEnable")));
		config_infoMap.put("dpd_threshold", GetStrValue(respA.get("DPDThreshold")));
		config_infoMap.put("dpd_retry", GetStrValue(respA.get("DPDRetry")));
		resB.put("config_info", config_infoMap);
		return resB;
	}
	
	/**
	 * IPSecVPN配置参数查询接口回参转换
	 * @param respA
	 * @return resB
	 */
	public static Map<String,Object> OpenIpsecVPN(WSRespObj respA)
	{
		Map<String,Object> resB=new HashMap<String,Object>();
		putBaseInfo(resB,respA);
		resB.put("loid", GetStrValue(respA.getLoid()));
		resB.put("loid_prev", GetStrValue(respA.getLoidPrev()));
		return resB;
	}
	
	/**
	 * 业务修改工单接口回参转换
	 * @param respA
	 * @return resB
	 */
	public static Map<String,Object> UpdateIpsecVPN(WSRespObj respA)
	{
		Map<String,Object> resB=new HashMap<String,Object>();
		putBaseInfo(resB,respA);
		resB.put("loid", GetStrValue(respA.getLoid()));
		resB.put("loid_prev", GetStrValue(respA.getLoidPrev()));
		return resB;
	}
	
	/**
	 * 业务销户工单接口回参转换
	 * @param respA
	 * @return resB
	 */
	public static Map<String,Object> DeleteIpsecVPN(WSRespObj respA)
	{
		Map<String,Object> resB=new HashMap<String,Object>();
		putBaseInfo(resB,respA);
		resB.put("loid", GetStrValue(respA.getLoid()));
		resB.put("loid_prev", GetStrValue(respA.getLoidPrev()));
		return resB;
	}
	
	/**
	 * 业务下发回调翼翮接口回参转换
	 * @param respA
	 * @return resB
	 */
	public static Map<String,Object> ServiceDoneReturn(WSRespObj respA)
	{
		Map<String,Object> resB=new HashMap<String,Object>();
		resB.put("requ_id", GetStrValue(respA.getCmdID()));
		resB.put("code", GetStrValue(respA.getRstCode()));
		return resB;
	}
	
	/**
	 * 错误回单
	 * @param code,reason
	 * @return
	 */
	/*public static Map<String,Object> wrongReturn(String request_id,String code,String reason)
	{
		Map<String,Object> resB=new HashMap<String,Object>();
		resB.put("requ_id", request_id);
		resB.put("code", codeTransMit(code));
		resB.put("reason", reason);
		return resB;
	}*/
	
	/**
	 * 把[]的结果换成空字符串
	 * @param obj
	 * @return
	 */
	public static String GetStrValue(Object obj){
		if (null == obj) {
			return "";
		}else if(obj instanceof JSONArray && (((JSONArray)obj).size()==0)){
			return "";
		}else if(obj.equals("[]")){
			return "";
		}else{
			return obj.toString();
		}
	}
	
	public static void main(String[] args) {
		String s="{\n    \"swagger\": \"2.0\",\n    \"info\": {\n        \"description\": \"DCI接口描述\n\",\n        \"version\": \"1.0.0\",\n        \"title\": \"DCI接口\"\n    },\n    \"host\": \"petstore.swagger.io\",\n    \"basePath\": \"/\",\n    \"schemes\": [\n        \"http\"\n    ],\n    \"paths\": {\n        \"/servopen/l3vpn/dci/compelete\": {\n            \"post\": {\n                \"tags\": [\n                    \"l3vpn\"\n                ],\n                \"summary\": \"报竣接口\",\n                \"description\": \"报竣\",\n                \"operationId\": \"intface2\",\n                \"consumes\": [\n                    \"application/json\",\n                    \"application/xml\"\n                ],\n                \"produces\": [\n                    \"application/json\",\n                    \"application/xml\"\n                ],\n                \"parameters\": [\n                    {\n                        \"name\": \"paras\",\n                        \"in\": \"body\",\n                        \"description\": \"报竣\",\n                        \"required\": true,\n                        \"schema\": {\n                            \"required\": [\n                                \"vpnLinkCode\",\n                                \"proInstId\"\n                            ],\n                            \"properties\": {\n                                \"vpnLinkCode\": {\n                                    \"type\": \"string\"\n                                },\n                                \"proInstId\": {\n                                    \"type\": \"string\"\n                                },\n                                \"operatedDate\": {\n                                    \"type\": \"string\"\n                                }\n                            }\n                        }\n                    }\n                ],\n                \"responses\": {\n                    \"200\": {\n                        \"description\": \"result info\",\n                        \"schema\": {\n                            \"properties\": {\n                                \"result\": {\n                                    \"type\": \"string\"\n                                },\n                                \"error_string\": {\n                                    \"type\": \"string\"\n                                }\n                            }\n                        }\n                    },\n                    \"404\": {\n                        \"description\": \"not found\"\n                    }\n                }\n            }\n        }\n    }\n}";
		System.out.println(s);
	}
}
