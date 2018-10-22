package ${packageName}.service;

/**
 * ${classDesc}
 * @author yufan
 */
public interface I${className?cap_first}Service {

    <#list methodList as method>
    /**
 	 * ${method.comment}。
 	 */
	ResponseMap ${method.name}(RequestMap requestMap) throws Exception;
    </#list>
}
