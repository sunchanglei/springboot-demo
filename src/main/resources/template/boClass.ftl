package ${packageName}.bo;


/**
 * ${classDesc}BO。
 * @author yunfan.
 */
@Setter
@Getter
@ToString
public class ${className?cap_first}Bo {

	<#list columnList as field>
	/** ${field.comment} */
	private ${field.colType} ${field.colName};
	</#list>
}
