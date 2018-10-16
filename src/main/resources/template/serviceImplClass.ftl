package ${packageName}.serviceImpl;

/**
 * ${classDesc}
 * @author yunfan
 */
@Service
public class ${className?cap_first}ServiceImpl implements I${className?cap_first}Service{

	@Autowired
	private ${className?cap_first}Dao ${className}Dao;

	@Autowired
	private ${className?cap_first}Dtf ${className}Dtf;

    <#list methodList as method>
	/**
 	 * ${method.comment}。
 	 */
	public ResponseMap ${method.name}(RequestMap requestMap) throws Exception {

        <#if method.params??>
        // 参数校验：
        <#list method.params as param>
        String ${param.name} = requestMap.getStringValueRequired("${param.desc}", "${param.name}");
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
}
