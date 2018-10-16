package ${packageName}.vo;

/**
 * ${classDesc}VO。
 * @author yunfan.
 */
@Setter
@Getter
@ToString
public class ${className?cap_first}Vo implements Serializable {

	private static final long serialVersionUID = 1L;

	<#list columnList as field>
	/** ${field.comment} */
	private ${field.colType} ${field.colName};
	</#list>
}
