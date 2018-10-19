/**
 * ********************** 版权声明 **********************************
 * 版权所有：Copyright (c) 2DFIRE Co., Ltd. 2014
 * <p>
 * 工程名称： retail-core
 * 创建者： guanhuangbai
 * 创建日期： 2014年9月9日
 * 创建记录： 创建类结构。
 * <p>
 * ************************* 变更记录 ********************************
 * 修改者：
 * 修改日期：
 * 修改记录：
 */

package com.boot.comm;

import com.boot.exception.BizException;
import com.boot.utils.DateUtil;
import com.boot.utils.JsonUtil;
import com.boot.utils.StringUtil;
import com.boot.utils.valite.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 请求参数的封装
 * @author yunfan
 */
public class RequestMap extends HashMap<String, Object> implements Serializable {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(RequestMap.class);

    private static final long serialVersionUID = 1L;

    /** 是否必须检查*/
    public static final boolean MUST_INPUT = true;
    public static final boolean NOT_MUST_INPUT = false;


    /**
     * getStringValue(获取String类型的值)
     *
     * @param key       参数描述
     * @return name 返回值描述
     */
    public String getStringRequired(String name, String key) throws BizException{
        String value = StringUtil.toStringNotNull(this.get(key));
        if(value.isEmpty()){
            throw new BizException(String.format(Message.MSG_CHK_000001, name));
        }
        return value;
    }

    /**
     * getStringValue(获取String类型的值)
     *
     * @param key       参数描述
     * @param mustInput 是否必须
     * @return name 返回值描述
     */
    public String getString(String name, String key, boolean mustInput) throws BizException{
        String value = StringUtil.toStringNotNull(this.get(key));
        if(mustInput && value.isEmpty()){
            throw new BizException(String.format(Message.MSG_CHK_000001, name));
        }
        return value;
    }

    /**
     * getStringValue(获取String类型的值)
     *
     * @param key       参数描述
     * @param mustInput 是否必须
     * @param lengthRang 长度范围，只能是两位，超出则默认取两位
     * @return name 返回值描述
     */
    public String getString(String name, String key, boolean mustInput, int... lengthRang) throws BizException{
        String value = StringUtil.toStringNotNull(this.get(key));
        if(mustInput && value.isEmpty()){
            throw new BizException(String.format(Message.MSG_CHK_000001, name));
        }
        validateRang(name,value.length(),lengthRang);
        return value;
    }

    /**
     * 验证字段长度范围
     * @param name
     * @param length
     * @param rang
     * @throws BizException
     */
    private void validateRang(String name, int length,int... rang) throws BizException{

        if(rang.length > 2){
            logger.error("字段长度有误");
        }

        if(rang.length == 1 && rang[0] != length){
            throw new BizException(String.format(Message.MSG_CHK_000003, new Object[]{name}));
        } else if(rang.length == 2 && (rang[0] > length || rang[1] < length)){
            throw new BizException(String.format(Message.MSG_CHK_000005, new Object[]{name,rang[0],rang[1]}));
        }
    }


    /**
     * getBooleanValue(获取Boolean类型的值)
     *
     * @param key       参数描述
     * @return name 返回值描述
     */
    public Boolean getBooleanRequired(String name, String key) throws BizException{
        Object value = this.get(key);

        if(value == null || value.toString().isEmpty()){
            throw new BizException(String.format(Message.MSG_CHK_000001, name));
        }

        if (!(value instanceof Boolean)){
            throw new BizException(String.format(Message.MSG_CHK_000002, name));
        }

        return (boolean)value;
    }

    /**
     * getBooleanValue(获取Boolean类型的值)
     *
     * @param key       参数描述
     * @param mustInput 是否必须
     * @return name 返回值描述
     */
    public Boolean getBoolean(String name, String key, boolean mustInput) throws BizException{
        Object value = this.get(key);

        if(mustInput && (value == null || value.toString().isEmpty())){
            throw new BizException(String.format(Message.MSG_CHK_000001, name));
        }

        if (!(value instanceof Boolean)){
            throw new BizException(String.format(Message.MSG_CHK_000002, name));
        }

        return (boolean)value;
    }

    /**
     * getIntegerValue(获取Date类型的值)
     *
     * @param key       参数描述
     * @return name 返回值描述
     */
    public Integer getIntegerRequired(String name, String key) throws BizException{

        Object value = this.get(key);
        if(value == null || value.toString().isEmpty()){
            throw new BizException(String.format(Message.MSG_CHK_000001, name));
        }

        if (!(value instanceof Integer)){
            throw new BizException(String.format(Message.MSG_CHK_000002, name));
        }

        return Integer.parseInt(value.toString());
    }

    /**
     * getIntegerValue(获取Date类型的值)
     *
     * @param key       参数描述
     * @param mustInput 是否必须
     * @return name 返回值描述
     */
    public Integer getInteger(String name, String key, boolean mustInput) throws BizException{

        Object value = this.get(key);
        if(mustInput && (value == null || value.toString().isEmpty())){
            throw new BizException(String.format(Message.MSG_CHK_000001, name));
        }

        if (!(value instanceof Integer)){
            throw new BizException(String.format(Message.MSG_CHK_000002, name));
        }

        return Integer.parseInt(value.toString());
    }

    /**
     * getLongValue(获取Long类型的值)
     *
     * @param name 参数描述
     * @return name 返回值描述
     */
    public Long getLongRequired(String name, String key) throws BizException{

        Object value = this.get(key);
        if(value == null || value.toString().isEmpty()){
            throw new BizException(String.format(Message.MSG_CHK_000001, name));
        }

        if (!(value instanceof Long)){
            throw new BizException(String.format(Message.MSG_CHK_000002, name));
        }

        return Long.parseLong(value.toString());
    }

    /**
     * getLongValue(获取Long类型的值)
     *
     * @param name 参数描述
     * @return name 返回值描述
     */
    public Long getLong(String name, String key, boolean mustInput) throws BizException{

        Object value = this.get(key);
        if(mustInput && (value == null || value.toString().isEmpty())){
            throw new BizException(String.format(Message.MSG_CHK_000001, name));
        }

        if (!(value instanceof Long)){
            throw new BizException(String.format(Message.MSG_CHK_000002, name));
        }

        return Long.parseLong(value.toString());
    }

    /**
     * getFloatValue(获取Float类型的值)
     *
     * @param key       参数描述
     * @return name 返回值描述
     */
    public Float getFloatRequired(String name, String key) throws BizException{

        Object value = this.get(key);
        if(value == null || value.toString().isEmpty()){
            throw new BizException(String.format(Message.MSG_CHK_000001, name));
        }

        if (!(value instanceof Float)){
            throw new BizException(String.format(Message.MSG_CHK_000002, name));
        }

        return Float.parseFloat(value.toString());
    }

    /**
     * getFloatValue(获取Float类型的值)
     *
     * @param key       参数描述
     * @param mustInput 是否必须
     * @return name 返回值描述
     */
    public Float getFloat(String name, String key, boolean mustInput) throws BizException{

        Object value = this.get(key);
        if(mustInput && (value == null || value.toString().isEmpty())){
            throw new BizException(String.format(Message.MSG_CHK_000001, name));
        }

        if (!(value instanceof Float)){
            throw new BizException(String.format(Message.MSG_CHK_000002, name));
        }

        return Float.parseFloat(value.toString());
    }

    /**
     * getDoubleValue(获取Double类型的值)
     *
     * @param key       参数描述
     * @return name 返回值描述
     */
    public Double getDoubleRequired(String name, String key) throws BizException{

        Object value = this.get(key);
        if(value == null || value.toString().isEmpty()){
            throw new BizException(String.format(Message.MSG_CHK_000001, name));
        }

        if (!(value instanceof Float)){
            throw new BizException(String.format(Message.MSG_CHK_000002, name));
        }

        return Double.parseDouble(value.toString());
    }

    /**
     * getDoubleValue(获取Double类型的值)
     *
     * @param key       参数描述
     * @param mustInput 是否必须
     * @return name 返回值描述
     */
    public Double getDouble(String name, String key, boolean mustInput) throws BizException{

        Object value = this.get(key);
        if(mustInput && (value == null || value.toString().isEmpty())){
            throw new BizException(String.format(Message.MSG_CHK_000001, name));
        }

        if (!(value instanceof Float)){
            throw new BizException(String.format(Message.MSG_CHK_000002, name));
        }

        return Double.parseDouble(value.toString());
    }

    /**
     * getBigDecimalValue(获取BigDecimal类型的值)
     *
     * @param key       参数描述
     * @return name 返回值描述
     */
    public BigDecimal getBigDecimalRequired(String name, String key) throws BizException{

        Object value = this.get(key);
        if(value == null || value.toString().isEmpty()){
            throw new BizException(String.format(Message.MSG_CHK_000001, name));
        }

        if (!(value instanceof BigDecimal)){
            throw new BizException(String.format(Message.MSG_CHK_000002, name));
        }

        return new BigDecimal(value.toString());
    }

    /**
     * getBigDecimalValue(获取BigDecimal类型的值)
     *
     * @param key       参数描述
     * @param mustInput 是否必须
     * @return name 返回值描述
     */
    public BigDecimal getBigDecimal(String name, String key, boolean mustInput) throws BizException{

        Object value = this.get(key);
        if(mustInput && (value == null || value.toString().isEmpty())){
            throw new BizException(String.format(Message.MSG_CHK_000001, name));
        }

        if (!(value instanceof BigDecimal)){
            throw new BizException(String.format(Message.MSG_CHK_000002, name));
        }

        return new BigDecimal(value.toString());
    }

    /**
     * getDateValue(获取Date类型的值)
     *
     * @param key       参数描述
     * @return name 返回值描述
     */
    public Date getDateRequired(String name, String key) throws BizException{

        Object value = this.get(key);
        if(value == null || value.toString().isEmpty()){
            throw new BizException(String.format(Message.MSG_CHK_000001, name));
        }

        if (!(value instanceof Date)){
            throw new BizException(String.format(Message.MSG_CHK_000002, name));
        }

        return DateUtil.toDateFromStr(value.toString(),DateUtil.YYYY_MM_DD);
    }

    /**
     * getDateValue(获取Date类型的值)
     *
     * @param key       参数描述
     * @param mustInput 是否必须
     * @return name 返回值描述
     */
    @SuppressWarnings("deprecation")
    public Date getDate(String name, String key, boolean mustInput) throws BizException{

        Object value = this.get(key);
        if(mustInput && (value == null || value.toString().isEmpty())){
            throw new BizException(String.format(Message.MSG_CHK_000001, name));
        }

        if (!(value instanceof Date)){
            throw new BizException(String.format(Message.MSG_CHK_000002, name));
        }

        return new Date(value.toString());
    }

    /**
     * getValueByType(获取自定义类型的值)
     *
     * @param key 参数描述
     * @return name 返回值描述
     */
    @SuppressWarnings("unchecked")
    public <T> T getTypeRequired(String name, String key, Class<T> clazz) throws BizException{

        Object value = this.get(key);
        if(value == null || value.toString().isEmpty()){
            throw new BizException(String.format(Message.MSG_CHK_000001, name));
        }

        try {
            String message = Validator.validate(value);
            if (StringUtil.isNotEmpty(message)){
                throw new BizException(message);
            }
        } catch(Exception e){
            logger.error("",e);
        }

        return JsonUtil.toBeanFromStr(value.toString(),clazz);
    }

    /**
     * getValueByType(获取自定义类型的值)
     *
     * @param key 参数描述
     * @return name 返回值描述
     */
    @SuppressWarnings("unchecked")
    public <T> T getType(String name, String key, Class<T> clazz, boolean mustInput) throws BizException{

        Object value = this.get(key);
        if(mustInput && (value == null || value.toString().isEmpty())){
            throw new BizException(String.format(Message.MSG_CHK_000001, name));
        }

        try {
            String message = Validator.validate(value);
            if (StringUtil.isNotEmpty(message)){
                throw new BizException(message);
            }
        } catch(Exception e){
            logger.error("",e);
        }

        return JsonUtil.toBeanFromStr(value.toString(),clazz);
    }

    /**
     * getListByType(获取自定义类型的集合值)
     *
     * @param key 参数描述
     * @return name 返回值描述
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getListTypeRequired(String name, String key, Class<T> clazz) throws BizException{

        Object value = this.get(key);
        if(value == null || value.toString().isEmpty()){
            throw new BizException(String.format(Message.MSG_CHK_000001, name));
        }

        List<T> list = JsonUtil.toListFromJsonStr(value.toString(),clazz);
        if(list.isEmpty()){
            throw new BizException(String.format(Message.MSG_CHK_000001, name));
        }

        return list;
    }

    /**
     * getListByType(获取自定义类型的集合值)
     *
     * @param key 参数描述
     * @return name 返回值描述
     */
    public <T> List<T> getListType(String name, String key, Class<T> clazz, boolean mustInput) throws BizException{

        Object value = this.get(key);
        if(mustInput && (value == null || value.toString().isEmpty())){
            throw new BizException(String.format(Message.MSG_CHK_000001, name));
        }

        List<T> list = JsonUtil.toListFromJsonStr(value.toString(),clazz);
        if(list.isEmpty()){
            throw new BizException(String.format(Message.MSG_CHK_000001, name));
        }

        return list;
    }
}
