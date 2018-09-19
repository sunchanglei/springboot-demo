<#if method.colType == "list_one">
    /**
     * 查询${method.comment}信息列表。
     * @param ${args.pone.colName} ${args.pone.comment}
     */
    public List<${method.colName?cap_first}Bo> listBy${args.pone.colName?cap_first}(@Param("${args.pone.colName}") ${args.pone.colType} ${args.pone.colName});
<#elseif method.colType == "list_two">
    /**
     * 查询${method.comment}信息列表。
     * @param ${args.pone.colName} ${args.pone.comment}
     * @param ${args.ptwo.colName} ${args.ptwo.comment}
     */
    public List<${method.colName?cap_first}Bo> listBy${args.pone.colName?cap_first}And${args.ptwo.colName?cap_first}(@Param("${args.pone.colName}") ${args.pone.colType} ${args.pone.colName},@Param("${args.ptwo.colName}") ${args.ptwo.colType} ${args.ptwo.colName});
<#elseif method.colType == "list_three">
    /**
     * 查询${method.comment}信息列表。
     * @param ${args.pone.colName} ${args.pone.comment}
     * @param ${args.ptwo.colName} ${args.ptwo.comment}
     * @param ${args.pthree.colName} ${args.pthree.comment}
     */
    public List<${method.colName?cap_first}Bo> listBy${args.pone.colName?cap_first}And${args.ptwo.colName?cap_first}And${args.pthree.colName?cap_first}(@Param("${args.pone.colName}") ${args.pone.colType} ${args.pone.colName},@Param("${args.ptwo.colName}") ${args.ptwo.colType} ${args.ptwo.colName},@Param("${args.pthree.colName}") ${args.pthree.colType} ${args.pthree.colName});
<#elseif method.colType == "list_all">
    /**
     * 查询${method.comment}全部信息列表。
     */
    public List<${method.colName?cap_first}Bo> listByAll();
<#elseif method.colType == "list_more">
    /**
     * 查询${method.comment}信息列表。
     * @param bo ${method.comment}Bo
     */
    public List<${method.colName?cap_first}Bo> listBy${method.colName?cap_first}Bo(${method.colName?cap_first}Bo bo);
<#elseif method.colType == "count_one" >
    /**
     * 查询${method.comment}信息。
     * @param ${args.pone.colName} ${args.pone.comment}
     */
    public int countBy${args.pone.colName?cap_first}(@Param("${args.pone.colName}") ${args.pone.colType} ${args.pone.colName});
<#elseif method.colType == "count_two" >
    /**
     * 查询${method.comment}信息。
     * @param ${args.pone.colName} ${args.pone.comment}
     * @param ${args.ptwo.colName} ${args.ptwo.comment}
     */
    public int countBy${args.pone.colName?cap_first}And${args.ptwo.colName?cap_first}(@Param("${args.pone.colName}") ${args.pone.colType} ${args.pone.colName},@Param("${args.ptwo.colName}") ${args.ptwo.colType} ${args.ptwo.colName});
<#elseif method.colType == "count_three" >
    /**
     * 查询${method.comment}信息。
     * @param ${args.pone.colName} ${args.pone.comment}
     * @param ${args.ptwo.colName} ${args.ptwo.comment}
     * @param ${args.pthree.colName} ${args.pthree.comment}
     */
    public int countBy${args.pone.colName?cap_first}And${args.ptwo.colName?cap_first}And${args.pthree.colName?cap_first}(@Param("${args.pone.colName}") ${args.pone.colType} ${args.pone.colName},@Param("${args.ptwo.colName}") ${args.ptwo.colType} ${args.ptwo.colName},@Param("${args.pthree.colName}") ${args.pthree.colType} ${args.pthree.colName});
<#elseif method.colType == "count_more">
    /**
     * 查询${method.comment}信息列表。
     * @param bo ${method.comment}Bo
     */
    public int countBy${method.colName?cap_first}Bo(${method.colName?cap_first}Bo bo);
<#elseif method.colType == "update">
    /**
     * 更新${method.comment}信息。
     * @param bo ${method.comment}Bo
     */
    public int updateBy${method.colName?cap_first}Bo(${method.colName?cap_first}Bo bo);
</#if>