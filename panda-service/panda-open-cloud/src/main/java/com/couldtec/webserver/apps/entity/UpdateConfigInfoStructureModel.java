package com.couldtec.webserver.apps.entity;

import io.swagger.annotations.ApiModelProperty;


/**
 * vpn下发参数
 * @author jlp
 *
 */
public class UpdateConfigInfoStructureModel {
	@ApiModelProperty(notes="IPSec 类型（Site-to-Site /PC-to-Site）,缺省为Site-to-Site")
	private String ipsec_type;
	@ApiModelProperty(notes="Site-to-Site 模式下对端IP 地址，Site-to-Site模式下必填")
	private String remote_ip;
	@ApiModelProperty(notes="PC-to-Site模式下对端域名。（Site-to-Site，可以不配。）仅在 IPSecType = PC-to-Site 时使用，且与RemoteIP互斥，两者仅能使用一个；如果IPSecType = PC-to-Site 则RemoteIP不配置")
	private String remote_domain;
	@ApiModelProperty(notes="对端子网")
	private String remote_subnet;
	@ApiModelProperty(notes="本地子网")
	private String local_subnet;
	@ApiModelProperty(notes="IKE协商方式：Main或者Aggressive，默认设置为Main。")
	private String exchange_mode;
	@ApiModelProperty(notes="IKE验证算法（MD5 / SHA1），缺省为SHA1")
	private String ike_auth_algorithm;
	@ApiModelProperty(notes="IKE验证方法（设置为PreShareKey/RsaSignature），缺省为PreShareKey。采用RsaSignature方式对IPsec VPN大规模部署，中心节点和分支节点均需导入证书，全网中每个设备均使用相同的CA证书，每个设备使用不同的本地证书用来标识自己的身份。如果 IKEAuthenticationMethod= PreShareKey，则节点IKEPreshareKey必须设置；如果IKEAuthenticationMethod= RsaSignature，则IKEPreshareKey不能设置")
	private String ike_auth__method;
	@ApiModelProperty(notes="IKE加密算法（DES/3DES /AES128 /AES192/ AES256）缺省为DES")
	private String ike_encryption;
	@ApiModelProperty(notes="IKE DH组（Group1/ Group2/ Group5/ Group14），缺省为Group1")
	private String ike_dhgroupp;
	@ApiModelProperty(notes="IKE身份类型（IP/Name），缺省为IP")
	private String ike_idtype;
	@ApiModelProperty(notes="IKE 本端名称")
	private String ike_localname;
	@ApiModelProperty(notes="IKE 对端名称")
	private String ike_remotename;
	@ApiModelProperty(notes="IKE预共享密钥，8～128个字符。无缺省值")
	private String ike_presharekey;
	@ApiModelProperty(notes="IPSec安全协议（AH/ESP /AH-ESP），缺省为ESP。")
	private String ipsec_transform;
	@ApiModelProperty(notes="IPsec验证算法（None/MD5/SHA1），缺省为MD5")
	private String esp_auth_algorithem;
	@ApiModelProperty(notes="IPsec加密算法(DES/3DES /AES128 /AES192/ AES256)，缺省为3DES")
	private String esp_encrypt_algorithm;
	@ApiModelProperty(notes="IPSec DH组（None/Group1 /Group2 /Group5 /Group14），缺省为None")
	private String ipsec_pfs;
	@ApiModelProperty(notes="设置IKE SA生命周期1200-86400秒，默认10800秒。")
	private String ike_saperiod;
	@ApiModelProperty(notes="设置IPsec SA时间生命周期，600-86400秒，默认3600秒。")
	private String ipsec_satime_period;
	@ApiModelProperty(notes="设置IPsec SA流量生命周期，KByte,2560-536870912Kbyte，默认1843200KByte")
	private String ipsec_satraffic_period;
	@ApiModelProperty(notes="AH验证算法（MD5/SHA1），缺省为MD5")
	private String ah_auth_algorithm;
	@ApiModelProperty(notes="Ipsec封装后报文的出接口，默认出接口是默认路由的出接口")
	private String ip_secout_interface;
	@ApiModelProperty(notes="Ipsec封装模式（Tunnel/Transport）缺省为Tunnel")
	private String ip_secencapsulation_mode;
	@ApiModelProperty(notes="DPD使能,默认0")
	private int dpd_enable;
	@ApiModelProperty(notes="DPD空闲时间，10-3600秒，默认10秒")
	private int dpd_threshold;
	@ApiModelProperty(notes="未收到DPD响应，再次尝试间隔 2~10秒，默认5秒")
	private int dpd_retry;
	
	public int getDpd_enable() {
		return dpd_enable;
	}
	public void setDpd_enable(int dpd_enable) {
		this.dpd_enable = dpd_enable;
	}
	public int getDpd_threshold() {
		return dpd_threshold;
	}
	public void setDpd_threshold(int dpd_threshold) {
		this.dpd_threshold = dpd_threshold;
	}
	public int getDpd_retry() {
		return dpd_retry;
	}
	public void setDpd_retry(int dpd_retry) {
		this.dpd_retry = dpd_retry;
	}
	public String getRemote_domain() {
		return remote_domain;
	}
	public void setRemote_domain(String remote_domain) {
		this.remote_domain = remote_domain;
	}
	public String getIp_secout_interface() {
		return ip_secout_interface;
	}
	public void setIp_secout_interface(String ip_secout_interface) {
		this.ip_secout_interface = ip_secout_interface;
	}
	public String getIp_secencapsulation_mode() {
		return ip_secencapsulation_mode;
	}
	public void setIp_secencapsulation_mode(String ip_secencapsulation_mode) {
		this.ip_secencapsulation_mode = ip_secencapsulation_mode;
	}
	public String getIpsec_type() {
		return ipsec_type;
	}
	public void setIpsec_type(String ipsec_type) {
		this.ipsec_type = ipsec_type;
	}
	public String getRemote_ip() {
		return remote_ip;
	}
	public void setRemote_ip(String remote_ip) {
		this.remote_ip = remote_ip;
	}
	public String getLocal_subnet() {
		return local_subnet;
	}
	public void setLocal_subnet(String local_subnet) {
		this.local_subnet = local_subnet;
	}
	public String getExchange_mode() {
		return exchange_mode;
	}
	public void setExchange_mode(String exchange_mode) {
		this.exchange_mode = exchange_mode;
	}
	public String getIke_auth_algorithm() {
		return ike_auth_algorithm;
	}
	public void setIke_auth_algorithm(String ike_auth_algorithm) {
		this.ike_auth_algorithm = ike_auth_algorithm;
	}
	public String getIke_auth__method() {
		return ike_auth__method;
	}
	public void setIke_auth__method(String ike_auth__method) {
		this.ike_auth__method = ike_auth__method;
	}
	public String getIke_encryption() {
		return ike_encryption;
	}
	public void setIke_encryption(String ike_encryption) {
		this.ike_encryption = ike_encryption;
	}
	public String getIke_dhgroupp() {
		return ike_dhgroupp;
	}
	public void setIke_dhgroupp(String ike_dhgroupp) {
		this.ike_dhgroupp = ike_dhgroupp;
	}
	public String getIke_idtype() {
		return ike_idtype;
	}
	public void setIke_idtype(String ike_idtype) {
		this.ike_idtype = ike_idtype;
	}
	public String getIke_localname() {
		return ike_localname;
	}
	public void setIke_localname(String ike_localname) {
		this.ike_localname = ike_localname;
	}
	public String getIke_remotename() {
		return ike_remotename;
	}
	public void setIke_remotename(String ike_remotename) {
		this.ike_remotename = ike_remotename;
	}
	public String getIke_presharekey() {
		return ike_presharekey;
	}
	public void setIke_presharekey(String ike_presharekey) {
		this.ike_presharekey = ike_presharekey;
	}
	public String getIpsec_transform() {
		return ipsec_transform;
	}
	public void setIpsec_transform(String ipsec_transform) {
		this.ipsec_transform = ipsec_transform;
	}
	public String getEsp_auth_algorithem() {
		return esp_auth_algorithem;
	}
	public void setEsp_auth_algorithem(String esp_auth_algorithem) {
		this.esp_auth_algorithem = esp_auth_algorithem;
	}
	public String getEsp_encrypt_algorithm() {
		return esp_encrypt_algorithm;
	}
	public void setEsp_encrypt_algorithm(String esp_encrypt_algorithm) {
		this.esp_encrypt_algorithm = esp_encrypt_algorithm;
	}
	public String getIpsec_pfs() {
		return ipsec_pfs;
	}
	public void setIpsec_pfs(String ipsec_pfs) {
		this.ipsec_pfs = ipsec_pfs;
	}
	public String getIke_saperiod() {
		return ike_saperiod;
	}
	public void setIke_saperiod(String ike_saperiod) {
		this.ike_saperiod = ike_saperiod;
	}
	public String getIpsec_satime_period() {
		return ipsec_satime_period;
	}
	public void setIpsec_satime_period(String ipsec_satime_period) {
		this.ipsec_satime_period = ipsec_satime_period;
	}
	public String getIpsec_satraffic_period() {
		return ipsec_satraffic_period;
	}
	public void setIpsec_satraffic_period(String ipsec_satraffic_period) {
		this.ipsec_satraffic_period = ipsec_satraffic_period;
	}
	public String getAh_auth_algorithm() {
		return ah_auth_algorithm;
	}
	public void setAh_auth_algorithm(String ah_auth_algorithm) {
		this.ah_auth_algorithm = ah_auth_algorithm;
	}
	public String getRemote_subnet() {
		return remote_subnet;
	}
	public void setRemote_subnet(String remote_subnet) {
		this.remote_subnet = remote_subnet;
	}
}
