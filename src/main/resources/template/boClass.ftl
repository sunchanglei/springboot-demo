package ${packageName}.bo;

@Setter
@Getter
@ToString
public class ${className?cap_first}Bo {

	<#list columnList as filed>
	/** ${filed.comment} */
	private ${filed.colType} ${filed.colName};
	</#list>
}
