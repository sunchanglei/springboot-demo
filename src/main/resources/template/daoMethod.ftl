<#list methodList as method>
    /**
 	 * ${method.comment}。
 	 */
	public ${method.retType} ${method.name}(${method.paramTypeStr}) {
		return ${className}Mapper.${method.name}(${method.paramStr});
	}
</#list>