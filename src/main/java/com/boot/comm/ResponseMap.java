/************************* 版权声明 **********************************
 * 版权所有：Copyright (c) 2DFIRE Co., Ltd. 2014 
 *
 * 工程名称： retail-core
 * 创建者： guanhuangbai  
 * 创建日期： 2014年9月4日
 * 创建记录： 创建类结构。
 *
 * ************************* 变更记录 ********************************
 * 修改者： 
 * 修改日期：
 * 修改记录：
 *
 **/

package com.boot.comm;

import java.io.Serializable;
import java.util.HashMap;

/** 
 * 
 * @author yufan
 */
public class ResponseMap extends HashMap<String, Object> implements Serializable {
	
    private static final long serialVersionUID = -2168621342070842380L;
    
    public static final String RETURN = "return";//返回消息标识
    
    public static final String MESSAGE = "message";//返回错误码

    /**
     * 返回成功消息标志
     */
    public static final String SUCCESS = "success";
    /**
     * 返回失败消息标志
     */
    public static final String FAIL = "fail";
    
    /**
     * setSuccessReturn(设置返回正常结果)   
     * @return  name  返回值描述
     * @Exception 异常对象
     */
    public void setSuccessReturn() {
        this.put(RETURN, SUCCESS);
        this.put(MESSAGE, null);
    }
    
    /**
     * setFailReturn(设置返回异常信息)   
     * @return  name  返回值描述
     * @Exception 异常对象
     */
    public void setFailReturn(String message) {
        this.put(RETURN, FAIL);
        this.put(MESSAGE, message);
    }
}
