<#list methodList as method>
    /**
 	 * ${method.comment}。
 	 */
	ResponseMap ${method.name}(RequestMap requestMap) throws Exception;
</#list>
