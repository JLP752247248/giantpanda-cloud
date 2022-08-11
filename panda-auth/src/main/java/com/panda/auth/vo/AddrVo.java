
package com.panda.auth.vo;

import com.panda.auth.user.entity.Addr;
import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-10  13:50
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class AddrVo  {

    /***/
    @NotNull
    private Long userId;
    /***/
    @NotNull
    private String address;
    /***/
    @NotNull
    private String address2;
    /***/
    @NotNull
    private String city;
    /***/
    @NotNull
    private String country;
    /***/
    @NotNull
    private String zipCode;

    public Addr buildAddr(){
        Addr tempAddr =new Addr();
            /***/
           tempAddr.setUserId(userId);
            /***/
           tempAddr.setAddress(address);
            /***/
           tempAddr.setAddress2(address2);
            /***/
           tempAddr.setCity(city);
            /***/
           tempAddr.setCountry(country);
            /***/
           tempAddr.setZipCode(zipCode);
        return tempAddr;
    }
}


