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
 	 */
	public ${method.retType} ${method.name}(${method.paramTypeStr}) {
		return ${className}Mapper.${method.name}(${method.paramStr});
	}
	</#list>

    public static ${className?cap_first}Vo to${className?cap_first}Vo(${className?cap_first}Bo bo){

		${className?cap_first}Vo vo = new ${className?cap_first}Vo();
		BeanUtil.copy(vo,bo);

		return vo;
	}

}
