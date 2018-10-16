<#list methodList as method>
	/**
 	 * ${method.comment}。
 	 */
	public ResponseMap ${method.name}(RequestMap requestMap) throws Exception {

        <#if method.params??>
        // 参数校验：
        <#list method.params as param>
        ${param.type} ${param.name} = requestMap.get${param.type}ValueRequired("${param.name}", "${param.comment}");
        </#list>
        </#if>

        // 数据查询：
        List<${className?cap_first}Bo> boList = ${className}Dao.${method.name}(${method.paramStr});
        if (boList == null || boList.isEmpty()){
            return new ResponseMap();
        }

        // 数据解析：
        List<${className?cap_first}Vo> voList = new ArrayList();
        for (${className?cap_first}Bo bo : boList){
            voList.add(${className}Dtf.to${className?cap_first}Vo(bo));
        }

        // 结果返回：
        ResponseMap resMap = new ResponseMap();
        resMap.put("${method.retName}",voList);//${method.retDesc}
        return resMap;
        }
</#list>