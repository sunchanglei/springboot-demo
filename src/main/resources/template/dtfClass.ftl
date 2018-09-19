package ${packageName}.dtf;

/**
 * ${classDesc}（数据转换）
 * @author yunfan.
 */
public class ${className?cap_first}Dtf {

    public static ${className?cap_first}Vo to${className?cap_first}Vo(${className?cap_first}Bo bo){

    	if(bo == null){
    		return null;
    	}

		${className?cap_first}Vo vo = new ${className?cap_first}Vo();
		BeanUtils.copyProperties(vo,bo);

		return vo;
	}
}
