<#list methodList as method>
    <#if method.type = "list" || method.type = "page">
    /**
     * ${method.comment}。
        <#list method.params as param>
     * @param ${param.name} ${param.comment}
        </#list>
     */
    <#if method.type = "list" || method.type = "page">
    List<${className?cap_first}Bo> ${method.name}(${method.paramTypeStr});
    <#elseif method.type = "query">
    ${className?cap_first}Bo ${method.name}(${method.paramTypeStr});
    <#elseif method.type = "count" || method.type = "insert" || method.type = "update">
    int ${method.name}(${method.paramTypeStr});
    </#if>
</#list>