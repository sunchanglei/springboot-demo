package ${packageName}.serviceImpl;

/**
 * ${classDesc}业务类
 * @author yunfan
 */
@Service
public class ${className?cap_first}ServiceImpl implements I${className?cap_first}Service{

	@Autowired
	private ${className?cap_first}Dao ${className}Dao;

    <#list methodList as method>
	/**
 	 * ${method.comment}。
 	 */
	public ResponseMap ${method.name}(RequestMap requestMap) throws Exception {

        <#if method.params??>
        // 参数校验：
        <#list method.params as param>
        String ${param.name} = requestMap.getStringRequired("${param.comment}","${param.name}");
        </#list>
        </#if>

        <#if method.type = "list" || method.type = "page">
        // 数据查询：
        List<${className?cap_first}Bo> boList = ${className}Dao.${method.name}(${method.paramStr});
        if (boList == null || boList.isEmpty()){
            return new ResponseMap();
        }

        // 数据解析：
        List<${className?cap_first}Vo> voList = new ArrayList();
        for (${className?cap_first}Bo bo : boList){
            voList.add(${className}Dao.to${className?cap_first}Vo(bo));
        }

        // 结果返回：
        ResponseMap resMap = new ResponseMap();
        resMap.put("${method.retName}",voList);//${method.retDesc}
        return resMap;
        <#elseif method.type = "query">

        // 数据查询：
        ${className?cap_first}Bo bo = ${className}Dao.${method.name}(${method.paramStr});
        if (bo == null){
            return new ResponseMap();
        }

        // 数据解析：
        ${className?cap_first}Vo vo = ${className}Dao.to${className?cap_first}Vo(bo)

        // 结果返回：
        ResponseMap resMap = new ResponseMap();
        resMap.put("${method.retName}",vo);//${method.retDesc}
        return resMap;
        <#elseif method.type = "count">

        // 数据查询：
        int count = ${className}Dao.${method.name}(${method.paramStr});

        // 结果返回：
        ResponseMap resMap = new ResponseMap();
        resMap.put("${method.retName}",count);//${method.retDesc}
        return resMap;
        </#if>
    }
    </#list>
}
