<#list methodList as method>
    <#if method.type == "list_one">
    <!--${method.comment}-->
    <select id="listBy${method.params[0].name?cap_first}" resultMap="BaseResultMap" parameterType="java.lang.String">
        select <include refid="Base_Column_List" />
        from ${tableName}
        where
        ${method.params[0].name} = ${r"#{"}${method.params[0].name}${r"}"}
    </select>
    <#elseif method.type == "list_two">
    <!--${method.comment}-->
    <select id="listBy${method.params[0].name?cap_first}And${method.params[1].name?cap_first}" resultMap="BaseResultMap" parameterType="java.lang.String">
        select <include refid="Base_Column_List" />
        from ${tableName}
        where
        ${method.params[0].name} = ${r"#{"}${method.params[0].name}${r"}"}
        and ${method.params[1].name} = ${r"#{"}${method.params[1].name}${r"}"}
    </select>
    <#elseif method.type == "list_three">
    <!--${method.comment}-->
    <select id="listBy${method.params[0].name?cap_first}And${method.params[1].name?cap_first}And${method.params[2].name?cap_first}" resultMap="BaseResultMap" parameterType="java.lang.String">
        select <include refid="Base_Column_List" />
        from ${tableName}
        where
        ${method.params[0].name} = ${r"#{"}${method.params[0].name}${r"}"}
        and ${method.params[1].name} = ${r"#{"}${method.params[1].name}${r"}"}
        and ${method.params[2].name} = ${r"#{"}${method.params[2].name}${r"}"}
    </select>
    <#elseif method.type == "list_all">
    <!--${method.comment}-->
    <select id="listByAll" resultMap="BaseResultMap" parameterType="java.lang.String">
        select <include refid="Base_Column_List" />
        from ${tableName}
    </select>
    <#elseif method.type == "list_more">
    <!--${method.comment}-->
    <select id="listBy${className?cap_first}Bo" resultMap="BaseResultMap" parameterType="${packageName}.bo.${className?cap_first}Bo">
        select <include refid="Base_Column_List" />
        from ${tableName}
        where
        ${name} = ${r"#{"}${name}${r"}"}
    </select>
    <#elseif method.type == "count_one" >
    <!--${method.comment}-->
    <select id="countBy${method.params[0].name?cap_first}" resultType="java.lang.Integer" parameterType="java.lang.String">
        select count(1)
        from ${tableName}
        where
        ${method.params[0].name} = ${r"#{"}${method.params[0].name}${r"}"}
    </select>
    <#elseif method.type == "count_two" >
    <!--${method.comment}-->
    <select id="countBy${method.params[0].name?cap_first}And${method.params[1].name?cap_first}" resultType="java.lang.Integer" parameterType="java.lang.String">
        select count(1)
        from ${tableName}
        where
        ${method.params[0].name} = ${r"#{"}${method.params[0].name}${r"}"}
        and ${method.params[1].name} = ${r"#{"}${method.params[1].name}${r"}"}
    </select>
    <#elseif method.type == "count_three" >
    <!--${method.comment}-->
    <select id="countBy${method.params[0].name?cap_first}And${method.params[1].name?cap_first}And${method.params[2].name?cap_first}" resultType="java.lang.Integer" parameterType="java.lang.String">
        select count(1)
        from ${tableName}
        where
        ${method.params[0].name} = ${r"#{"}${method.params[0].name}${r"}"}
        and ${method.params[1].name} = ${r"#{"}${method.params[1].name}${r"}"}
        and ${method.params[2].name} = ${r"#{"}${method.params[2].name}${r"}"}
    </select>
    <#elseif method.type == "count_more">
    <!--${method.comment}-->
    <select id="listBy${className?cap_first}Bo" resultType="java.lang.Integer" parameterType="${packageName}.bo.${className?cap_first}Bo">
        select <include refid="Base_Column_List" />
        from ${tableName}
        where
        ${bo.name} = ${r"#{"}${bo.name}${r"}"}
    </select>
    <#elseif method.type == "update">
    <!--${method.comment}-->
    <update id="updateBy${className?cap_first}Bo" parameterType="${packageName}.bo.${className?cap_first}Bo">
        update ${tableName}
        set ${name} =  = ${r"#{"}${name}${r"}"}
        where
        ${name} = ${r"#{"}${name}${r"}"}
    </update>
    </#if>
</#list>