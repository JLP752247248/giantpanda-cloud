package com.couldtec.webserver.util.test;
/**
 * 测试模式的时候返回的写死数据
 * @author jlp
 *
 */
public class GetRespXMLStr {
	public static String xmlTestRespStr(String method, String reqId) {
		if (method.equals("QueryGetWay")) {
			return "<root><CmdID>"
					+ reqId
					+ "</CmdID><RstCode>0</RstCode><RstMsg>成功</RstMsg>"
					+ "<Param><DeviceSN>abcdefg</DeviceSN><Loid>571SN000001</Loid><LoidPrev></LoidPrev><DeviceType>2</DeviceType><DeviceVendor>格林维尔</DeviceVendor><DeviceModel>MSG2100E-UPON-16V-AC-T</DeviceModel><Softwareversion>MSG2100E-16V_SYSTEM_2.24.199_20170613.BIN</Softwareversion><Hardwareversion>1.0.0</Hardwareversion><IsNet>1</IsNet><IsIpsecVPN></IsIpsecVPN><WanType>1</WanType><IpAddr>10.100.152.175</IpAddr><IpType>1</IpType><Online></Online><Status>0</Status><IpsecStatus>0</IpsecStatus></Param></root>";
		} else if (method.equals("QueryServIpAddr")) {
			return "<root><CmdID>"
					+ reqId
					+ "</CmdID><RstCode>0</RstCode>"
					+ "<RstMsg>结果描述</RstMsg>"
					+ "<Param>"
					+ "<ServIpAddr>172.12.0.46</ServIpAddr>"
					+ "<Loid>571SN000001</Loid>"
					+ "<LoidPrev>99990000088</LoidPrev>"
					+ "</Param></root>";
		} else if (method.equals("QueryVpnStats")) {
			return "<root><CmdID>"
					+ reqId
					+ "</CmdID><RstCode>0</RstCode><RstMsg>成功</RstMsg><Param><IpescVpnStatus>Connected</IpescVpnStatus><Loid>571SN000001</Loid><LoidPrev>99990000088</LoidPrev></Param></root>";
		} else if (method.equals("QueryDevOnline")) {
			return "<root><CmdID>"
					+ reqId
					+ "</CmdID><RstCode>0</RstCode><RstMsg>成功</RstMsg><Param><OnlineStatus>1</OnlineStatus><Loid>571SN000001</Loid><LoidPrev>99990000088</LoidPrev></Param></root>";
		} else if (method.equals("PingDiagnostic")) {
			return "<root><CmdID>"
					+ reqId
					+ "</CmdID><RstCode>0</RstCode>"
					+ "<RstMsg>成功</RstMsg><Param>"
					+ "<Loid>571SN000001</Loid>"
					+ "<LoidPrev>99990000088</LoidPrev>"
					+ "<DevSn>abcdefg</DevSn>"
					+ "<SuccesNum>4</SuccesNum>"
					+ "<FailNum>0</FailNum>"
					+ "<AvgResponseTime>0.354</AvgResponseTime>"
					+ "<MinResponseTime>0.269</MinResponseTime>"
					+ "<MaxResponseTime>0.439</MaxResponseTime>"
					+ "<PacketLossRate>0%</PacketLossRate>"
					+ "<IPOrDomainName>192.168.18.70</IPOrDomainName></Param>"
					+ "</root>";
		} else if (method.equals("QueryBssStats")) {
			return "<root><CmdID>"
					+ reqId
					+ "</CmdID><RstCode>0</RstCode>"
					+ "<RstMsg>成功</RstMsg><Param>"
					+ "<BssStats>1</BssStats>"
					+ "<Loid>571SN000001</Loid>"
					+ "<LoidPrev>99990000088</LoidPrev>"
					+ "</Param></root>";
		} else if (method.equals("ServiceDone")) {
			return "<root><CmdID>"
					+ reqId
					+ "</CmdID><RstCode>0</RstCode>"
					+ "<RstMsg>成功</RstMsg><Param>"
					+ "<ServiceDoneStats>1</ServiceDoneStats>"
					+ "<Loid>571SN000001</Loid>"
					+ "<LoidPrev>99990000088</LoidPrev>"
					+ "</Param></root>";
		} else if (method.equals("GetWayReboot")) {
			return "<root><CmdID>"
					+ reqId
					+ "</CmdID><RstCode>0</RstCode><RstMsg>成功</RstMsg>"
					+ "<Param>"
					+ "<RebootStats>1</RebootStats>"
					+ "<Loid>571SN000001</Loid>"
					+ "<LoidPrev>99990000088</LoidPrev>"
					+ "</Param>"
					+ "</root>";
		} else if (method.equals("QueryConfigure")) {
			return "<root><CmdID>"
					+ reqId
					+ "</CmdID><RstCode>0</RstCode>"
					+ "<RstMsg>成功</RstMsg><Param>"
					+ "<Loid>571SN000001</Loid>"
					+ "<LoidPrev>99990000088</LoidPrev>"
					+ "<IPSecType>Site-to-Site</IPSecType>"
					+ "<RemoteIP>172.13.1.1</RemoteIP>"
					+ "<ExchangeMode>Main</ExchangeMode>"
					+ "<IKEAuthenticationAlgorithm>DES</IKEAuthenticationAlgorithm>"
					+ "<IKEAuthenticationMethod>PreShareKey</IKEAuthenticationMethod>"
					+ "<IKEEncryptionAlgorithm>DES</IKEEncryptionAlgorithm>"
					+ "<IKEDHGroup>Group1</IKEDHGroup>"
					+ "<IKEIDType>IP</IKEIDType>"
					+ "<IKELocalName>IKE 本端名称</IKELocalName>"
					+ "<IKERemoteName>IKE 对端名称</IKERemoteName>"
					+ "<IKEPreshareKey>IKE 预共享密钥</IKEPreshareKey>"
					+ "<IPSecTransform>ESP</IPSecTransform>"
					+ "<ESPAuthenticationAlgorithm>MD5"
					+ "</ESPAuthenticationAlgorithm><ESPEncryptionAlgorithm>3DES"
					+ "</ESPEncryptionAlgorithm><IPSecPFS>None</IPSecPFS><IKESAPeriod>10800"
					+ "</IKESAPeriod><IPSecSATimePeriod>3600</IPSecSATimePeriod>"
					+ "<IPSecSATrafficPeriod>1843200Kbyte</IPSecSATrafficPeriod>"
					+ "<AHAuthenticationAlgorithm>MD5</AHAuthenticationAlgorithm>"
					+ "</Param></root>";
		} else if (method.equals("OpenIpsecVPN")) {
			return "<root><CmdID>"
					+ reqId
					+ "</CmdID>"
					+ "<RstCode>0</RstCode><RstMsg>成功</RstMsg>"
					+ "<Loid>571SN000001</Loid>"
					+ "<LoidPrev>99990000088</LoidPrev></root>";
		} else if (method.equals("UpdateIpsecVPN")) {
			return "<root><CmdID>"
					+ reqId
					+ "</CmdID><RstCode>0</RstCode><RstMsg>成功</RstMsg><Loid>571SN000001</Loid><LoidPrev>99990000088</LoidPrev></root>";
		} else if (method.equals("DeleteIpsecVPN")) {
			return "<root><CmdID>"
					+ reqId
					+ "</CmdID><RstCode>0</RstCode><RstMsg>成功</RstMsg><Loid>571SN000001</Loid><LoidPrev>99990000088</LoidPrev></root>";
		}
		return "<root><CmdID>"
				+ reqId
				+ "</CmdID><RstCode>0</RstCode><RstMsg>成功</RstMsg><Loid>571SN000001</Loid><LoidPrev>99990000088</LoidPrev></root>";
	}
}
