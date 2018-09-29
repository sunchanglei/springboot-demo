package com.boot.comm;

/**
 * 项目名称：OrderService
 * 类名称：Message
 * 类描述： 消息类
 * 创建时间：2014-12-07
 * 
 * @author pijiu
 * @version 1.0
 */
public final class Message {
	
	/** 请求验证 */

	public static final String MSG_CHK_000001 = "%s为必须输入项目！";
	
	public static final String MSG_CHK_000002 = "%s格式不正确！";
	
	public static final String MSG_CHK_000003 = "%s长度不正确！";
	
	public static final String MSG_CHK_000004 = "%s长度不能超过%s位！";

    public static final String MSG_CHK_000005 = "%s长度必须在%s-%s之间！";

	public static final String MSG_CHK_000006 = "日期类型格式错误！";
	
	/** 消息列表 */

	public static final String MSG_SYS_000001 = "系统异常，请与管理员联系！";

	public static final String MSG_SYS_000002 = "没有权限接入系统！";
	
	public static final String MSG_SYS_000003 = "签名验证失败！";
	
	public static final String MSG_ERR_000001 = "该数据不存在！";
	
	public static final String MSG_ERR_000002 = "您没有操作该信息的权限！";
	
	public static final String MSG_ERR_000003 = "该数据已被别人修改或删除！";
}
