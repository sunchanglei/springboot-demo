package ${packageName}.mapper;

@Mapper
public interface ${className?cap_first}Mapper extends BaseMapper<${className?cap_first}Bo>{

    <#list methodList as method>
    <#if method.type == "list_one">
    /**
     * ${method.comment}。
        <#list method.params as param>
     * @param ${param.name} ${param.comment}
        </#list>
     */
    public List<${className?cap_first}Bo> listBy${method.params[0].name?cap_first}(@Param("${method.params[0].name}") ${method.params[0].type} ${method.params[0].name});
    <#elseif method.type == "list_two">
    /**
     * ${method.comment}。
        <#list method.params as param>
     * @param ${param.name} ${param.comment}
        </#list>
     */
    public List<${className?cap_first}Bo> listBy${method.params[0].name?cap_first}And${method.params[1].name?cap_first}(@Param("${method.params[0].name}") ${method.params[0].type} ${method.params[0].name},@Param("${method.params[1].name}") ${method.params[1].type} ${method.params[1].name});
    <#elseif method.type == "list_three">
    /**
     * 查询${method.comment}信息列表。
        <#list method.params as param>
     * @param ${param.name} ${param.comment}
        </#list>
     */
    public List<${className?cap_first}Bo> listBy${method.params[0].name?cap_first}And${method.params[1].name?cap_first}And${method.params[2].name?cap_first}(@Param("${method.params[0].name}") ${method.params[0].type} ${method.params[0].name},@Param("${method.params[1].name}") ${method.params[1].type} ${method.params[1].name},@Param("${method.params[2].name}") ${method.params[2].type} ${method.params[2].name});
    <#elseif method.type == "list_all">
    /**
     * 查询全部${method.comment}。
     */
    public List<${className?cap_first}Bo> listByAll();
    <#elseif method.type == "list_more">
    /**
     * 查询${method.comment}。
     * @param bo ${method.comment}Bo
     */
    public List<${className?cap_first}Bo> listBy${className?cap_first}Bo(${className?cap_first}Bo bo);
    <#elseif method.type == "count_one" >
    /**
     * 查询${method.comment}信息。
        <#list method.params as param>
     * @param ${param.name} ${param.comment}
        </#list>
     */
    public int countBy${method.params[0].name?cap_first}(@Param("${method.params[0].name}") ${method.params[0].type} ${method.params[0].name});
    <#elseif method.type == "count_two" >
    /**
     * 查询${method.comment}信息。
        <#list method.params as param>
     * @param ${param.name} ${param.comment}
        </#list>
     */
    public int countBy${method.params[0].name?cap_first}And${method.params[1].name?cap_first}(@Param("${method.params[0].name}") ${method.params[0].type} ${method.params[0].name},@Param("${method.params[1].name}") ${method.params[1].type} ${method.params[1].name});
    <#elseif method.type == "count_three" >
    /**
     * 查询${method.comment}信息。
        <#list method.params as param>
     * @param ${param.name} ${param.comment}
        </#list>
     */
    public int countBy${method.params[0].name?cap_first}And${method.params[1].name?cap_first}And${method.params[2].name?cap_first}(@Param("${method.params[0].name}") ${method.params[0].type} ${method.params[0].name},@Param("${method.params[1].name}") ${method.params[1].type} ${method.params[1].name},@Param("${method.params[2].name}") ${method.params[2].type} ${method.params[2].name});
    <#elseif method.type == "count_more">
    /**
     * 统计${method.comment}。
     * @param bo ${method.comment}Bo
     */
    public int countBy${className?cap_first}Bo(${className?cap_first}Bo bo);
    <#elseif method.type == "update">
    /**
     * 更新${method.comment}信息。
     * @param bo ${method.comment}Bo
     */
    public int updateBy${className?cap_first}Bo(${className?cap_first}Bo bo);
    </#if>
    </#list>
}