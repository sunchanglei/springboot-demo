package ${packageName}.vo;

/**
 * ${classDesc}（应用端字段-VO）
 * @author yunfan.
 */
@Setter
@Getter
@ToString
public class ${className?cap_first}Vo implements Serializable {

	private static final long serialVersionUID = 1L;

	<#list columnList as filed>
	/** ${filed.comment} */
	private ${filed.colType} ${filed.colName};
	</#list>
}
