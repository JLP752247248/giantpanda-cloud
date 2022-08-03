package com.couldtec.webserver.apps.convert;

import java.util.Map;

import com.couldtec.webserver.apps.entity.WSReqObj;
import com.couldtec.webserver.util.StringUtil;

/**
 * 接口入参转换，restful入参-》webService入参
 */
public  class MapToWSReq
{
	/**
	 * 网关信息查询接口入参转换，restful-》webService
	 * @param paramB
	 * @return resA
	 */
	public static WSReqObj QueryGetWay(Map<String,String> paramB)
	{
		WSReqObj resA=new WSReqObj();
		resA.setCmdID(paramB.get("requ_id"));
		resA.setCmdType("CX_01");
		resA.setClientType("5");
		resA.setDealDate(paramB.get("ts"));
		resA.getParam().put("UserInfoType", paramB.get("key_type"));
		resA.getParam().put("UserInfo", StringUtil.getStringValue(paramB.get("quer_key")));
		return resA;
	}
	
	/**
	 * 网关业务ip地址查询接口入参转换，restful-》webService
	 * @param paramB
	 * @return resA
	 */
	public static WSReqObj QueryServIpAddr(Map<String,String> paramB)
	{
		WSReqObj resA=new WSReqObj();
		resA.setCmdID(paramB.get("requ_id"));
		resA.setCmdType("CX_01");
		resA.setClientType("5");
		resA.setDealDate(paramB.get("ts"));
		resA.getParam().put("UserInfoType", paramB.get("key_type"));
		resA.getParam().put("UserInfo", paramB.get("quer_key"));
		return resA;
	}
	
	/**
	 * 网关IPSec状态查询接口入参转换，restful-》webService
	 * @param paramB
	 * @return
	 */
	public static WSReqObj QueryVpnStats(Map<String,String> paramB)
	{
		WSReqObj resA=new WSReqObj();
		resA.setCmdID(paramB.get("requ_id"));
		resA.setCmdType("CX_01");
		resA.setClientType("5");
		resA.setDealDate(paramB.get("ts"));
		resA.getParam().put("UserInfoType", paramB.get("key_type"));
		resA.getParam().put("UserInfo", paramB.get("quer_key"));
		
		return resA;
	}
	
	/**
	 * 网关在线状态查询接口入参转换，restful-》webService
	 * @param paramB
	 * @return
	 */
	public static WSReqObj QueryDevOnline(Map<String,String> paramB)
	{
		WSReqObj resA=new WSReqObj();
		resA.setCmdID(paramB.get("requ_id"));
		resA.setCmdType("CX_01");
		resA.setClientType("5");
		resA.setDealDate(paramB.get("ts"));
		resA.getParam().put("UserInfoType", paramB.get("key_type"));
		resA.getParam().put("UserInfo", paramB.get("quer_key"));
		return resA;
	}
	
	/**
	 * Ping诊断接口入参转换，restful-》webService
	 * @param paramB
	 * @return
	 */
	public static WSReqObj PingDiagnostic(Map<String,String> paramB)
	{
		WSReqObj resA=new WSReqObj();
		resA.setCmdID(paramB.get("requ_id"));
		resA.setCmdType("CX_01");
		resA.setClientType("5");
		resA.setDealDate(paramB.get("ts"));
		resA.getParam().put("UserInfoType", paramB.get("key_type"));
		resA.getParam().put("UserInfo", paramB.get("quer_key"));
		resA.getParam().put("WanPassageWay", paramB.get("wanpassage_way"));
		resA.getParam().put("PackageByte", paramB.get("package_byte"));
		resA.getParam().put("IPOrDomainName", paramB.get("ipordomain_name"));
		resA.getParam().put("PackageNum", paramB.get("package_num"));
		resA.getParam().put("TimeOut", paramB.get("time_out"));
		return resA;
	}
	
	/**
	 *业务下发结果查询接口入参转换，restful-》webService
	 * @param paramB
	 * @return resA
	 */
	public static WSReqObj QueryBssStats(Map<String,String> paramB)
	{
		WSReqObj resA=new WSReqObj();
		resA.setCmdID(paramB.get("requ_id"));
		resA.setCmdType("CX_01");
		resA.setClientType("5");
		resA.setDealDate(paramB.get("ts"));
		resA.getParam().put("UserInfoType", paramB.get("key_type"));
		resA.getParam().put("UserInfo", paramB.get("quer_key"));
		return resA;
	}
	
	/**
	 * 业务下发接口入参转换，restful-》webService
	 * @param paramB
	 * @return resA
	 */
	public static WSReqObj ServiceDone(Map<String,String> paramB)
	{
		WSReqObj resA=new WSReqObj();
		resA.setCmdID(paramB.get("requ_id"));
		resA.setCmdType("CX_01");
		resA.setClientType("5");
		resA.setDealDate(paramB.get("ts"));
		//resA.setRollback(StringUtil.getStringValue(paramB.get("rollback")));
		resA.getParam().put("UserInfoType", paramB.get("key_type"));
		resA.getParam().put("UserInfo", paramB.get("quer_key"));
		
		return resA;
	}
	
	/**
	 * 网关重启接口入参转换，restful-》webService
	 * @param paramB
	 * @return resA
	 */
	public static WSReqObj GetWayReboot(Map<String,String> paramB)
	{
		WSReqObj resA=new WSReqObj();
		resA.setCmdID(paramB.get("requ_id"));
		resA.setCmdType("CX_01");
		resA.setClientType("5");
		resA.setDealDate(paramB.get("ts"));
		resA.getParam().put("UserInfoType", paramB.get("key_type"));
		resA.getParam().put("UserInfo", paramB.get("quer_key"));
		
		return resA;
	}
	
	/**
	 * IPSecVPN配置参数查询接口入参转换，restful-》webService
	 * @param paramB
	 * @return resA
	 */
	public static WSReqObj QueryConfigure(Map<String,String> paramB)
	{
		WSReqObj resA=new WSReqObj();
		resA.setCmdID(paramB.get("requ_id"));
		resA.setCmdType("CX_01");
		resA.setClientType("5");
		resA.setDealDate(paramB.get("ts"));
		resA.getParam().put("UserInfoType", paramB.get("key_type"));
		resA.getParam().put("UserInfo", paramB.get("quer_key"));
		
		return resA;
	}
	
	/**
	 * IPSecVPN配置参数查询接口入参转换，restful-》webService
	 * @param paramB
	 * @return
	 */
	public static WSReqObj OpenIpsecVPN(Map<String,String> paramB)
	{
		WSReqObj resA=new WSReqObj();
		resA.setCmdID(paramB.get("requ_id"));
		resA.setCmdType("CX_01");
		resA.setClientType("5");
		resA.setDealDate(paramB.get("ts"));
		resA.setServTypeId("27");
		resA.setOperateId("1");
		resA.getParam().put("UserInfoType", paramB.get("key_type"));
		resA.getParam().put("UserInfo", paramB.get("quer_key"));
		resA.getParam().put("UserType", paramB.get("user_type"));
		resA.getParam().put("RequestID", paramB.get("requ_id"));
		resA.getParam().put("IPSecType", paramB.get("ipsec_type"));
		resA.getParam().put("RemoteSubnet", paramB.get("remote_subnet"));
		resA.getParam().put("LocalSubnet", paramB.get("local_subnet"));
		resA.getParam().put("RemoteIP", paramB.get("remote_ip"));
		resA.getParam().put("RemoteDomain", paramB.get("remote_domain"));
		resA.getParam().put("IPSecOutInterface", paramB.get("ip_secout_interface"));
		resA.getParam().put("IPSecEncapsulationMode", paramB.get("ip_secencapsulation_mode"));
		resA.getParam().put("ExchangeMode", paramB.get("exchange_mode"));
		resA.getParam().put("IKEAuthenticationAlgorithm", paramB.get("ike_auth_algorithm"));
		resA.getParam().put("IKEAuthenticationMethod", paramB.get("ike_auth__method"));
		resA.getParam().put("IKEEncryptionAlgorithm", paramB.get("ike_encryption"));
		resA.getParam().put("IKEDHGroup", paramB.get("ike_dhgroupp"));
		resA.getParam().put("IKEIDType", paramB.get("ike_idtype"));
		resA.getParam().put("IKELocalName", paramB.get("ike_localname"));
		resA.getParam().put("IKERemoteName", paramB.get("ike_remotename"));
		resA.getParam().put("IKEPreshareKey", paramB.get("ike_presharekey"));
		resA.getParam().put("IPSecTransform", paramB.get("ipsec_transform"));
		resA.getParam().put("ESPAuthenticationAlgorithm", paramB.get("esp_auth_algorithem"));
		resA.getParam().put("ESPEncryptionAlgorithm", paramB.get("esp_encrypt_algorithm"));
		resA.getParam().put("IPSecPFS", paramB.get("ipsec_pfs"));
		resA.getParam().put("IKESAPeriod", paramB.get("ike_saperiod"));
		resA.getParam().put("IPSecSATimePeriod", paramB.get("ipsec_satime_period"));
		resA.getParam().put("IPSecSATrafficPeriod", paramB.get("ipsec_satraffic_period"));
		resA.getParam().put("AHAuthenticationAlgorithm", paramB.get("ah_auth_algorithm"));
		resA.getParam().put("DPDEnable", paramB.get("dpd_enable"));
		resA.getParam().put("DPDThreshold", paramB.get("dpd_threshold"));
		resA.getParam().put("DPDRetry", paramB.get("dpd_retry"));
		return resA;
	}
	
	/**
	 * 业务修改工单接口入参转换，restful-》webService
	 * @param paramB
	 * @return
	 */
	public static WSReqObj UpdateIpsecVPN(Map<String,String> paramB)
	{
		WSReqObj resA=new WSReqObj();
		resA.setCmdID(paramB.get("requ_id"));
		resA.setCmdType("CX_01");
		resA.setClientType("5");
		resA.setDealDate(paramB.get("ts"));
		resA.setServTypeId("27");
		resA.setOperateId("2");
		resA.getParam().put("UserInfoType", paramB.get("key_type"));
		resA.getParam().put("UserInfo", paramB.get("quer_key"));
		resA.getParam().put("UserType", paramB.get("user_type"));
		resA.getParam().put("RequestID", paramB.get("requ_id"));
		resA.getParam().put("IPSecType", paramB.get("ipsec_type"));
		resA.getParam().put("RemoteIP", paramB.get("remote_ip"));
		resA.getParam().put("RemoteSubnet", paramB.get("remote_subnet"));
		resA.getParam().put("LocalSubnet", paramB.get("local_subnet"));
		resA.getParam().put("RemoteDomain", paramB.get("remote_domain"));
		resA.getParam().put("IPSecOutInterface", paramB.get("ip_secout_interface"));
		resA.getParam().put("IPSecEncapsulationMode", paramB.get("ip_secencapsulation_mode"));
		resA.getParam().put("ExchangeMode", paramB.get("exchange_mode"));
		resA.getParam().put("IKEAuthenticationAlgorithm", paramB.get("ike_auth_algorithm"));
		resA.getParam().put("IKEAuthenticationMethod", paramB.get("ike_auth__method"));
		resA.getParam().put("IKEEncryptionAlgorithm", paramB.get("ike_encryption"));
		resA.getParam().put("IKEDHGroup", paramB.get("ike_dhgroupp"));
		resA.getParam().put("IKEIDType", paramB.get("ike_idtype"));
		resA.getParam().put("IKELocalName", paramB.get("ike_localname"));
		resA.getParam().put("IKERemoteName", paramB.get("ike_remotename"));
		resA.getParam().put("IKEPreshareKey", paramB.get("ike_presharekey"));
		resA.getParam().put("IPSecTransform", paramB.get("ipsec_transform"));
		resA.getParam().put("ESPAuthenticationAlgorithm", paramB.get("esp_auth_algorithem"));
		resA.getParam().put("ESPEncryptionAlgorithm", paramB.get("esp_encrypt_algorithm"));
		resA.getParam().put("IPSecPFS", paramB.get("ipsec_pfs"));
		resA.getParam().put("IKESAPeriod", paramB.get("ike_saperiod"));
		resA.getParam().put("IPSecSATimePeriod", paramB.get("ipsec_satime_period"));
		resA.getParam().put("IPSecSATrafficPeriod", paramB.get("ipsec_satraffic_period"));
		resA.getParam().put("AHAuthenticationAlgorithm", paramB.get("ah_auth_algorithm"));
		
		return resA;
	}
	
	/**
	 * 业务销户工单接口入参转换，restful-》webService
	 * @param paramB
	 * @return resA
	 */
	public static WSReqObj DeleteIpsecVPN(Map<String,String> paramB)
	{
		WSReqObj resA=new WSReqObj();
		resA.setCmdID(paramB.get("requ_id"));
		resA.setCmdType("CX_01");
		resA.setClientType("5");
		resA.setDealDate(paramB.get("ts"));
		resA.setServTypeId("27");
		resA.setOperateId("3");
		resA.getParam().put("UserInfoType", paramB.get("key_type"));
		resA.getParam().put("UserType", paramB.get("user_type"));
		resA.getParam().put("RequestID", paramB.get("requ_id"));
		resA.getParam().put("UserInfo", paramB.get("quer_key"));
		return resA;
	}
	
}
