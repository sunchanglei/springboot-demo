package ${packageName}.dao;

/**
 * ${classDesc}Dao。
 */
@Repository
public class ${className?cap_first}Dao {

	@Autowired
    private ${className?cap_first}Mapper ${className}Mapper;

	<#list methodList as method>
    /**
     * ${method.comment}。
		<#list method.params as param>
	 * @param ${param.name} ${param.comment}
		</#list>
     */
	<#if method.type = "list" || method.type = "page">
	public List<${className?cap_first}Bo> ${method.name}(${method.paramTypeStr}) {
	<#elseif method.type = "query">
	public ${className?cap_first}Bo ${method.name}(${method.paramTypeStr}) {
	<#elseif method.type = "count" || method.type = "insert" || method.type = "update">
	public int ${method.name}(${method.paramTypeStr}) {
	</#if>
        return ${className}Mapper.${method.name}(${method.paramStr});
	}

	</#list>

    public static ${className?cap_first}Vo to${className?cap_first}Vo(${className?cap_first}Bo bo){

		${className?cap_first}Vo vo = new ${className?cap_first}Vo();
		BeanUtil.copy(vo,bo);

		return vo;
	}

}
