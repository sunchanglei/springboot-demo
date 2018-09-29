package ${packageName}.bo;


/**
 * ${classDesc}（数据库字段映射-BO）
 * @author yunfan.
 */
@Setter
@Getter
@ToString
public class ${className?cap_first}Bo {

	<#list columnList as filed>
	/** ${filed.comment} */
	private ${filed.colType} ${filed.colName};
	</#list>
}
