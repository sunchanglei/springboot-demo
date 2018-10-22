<#list methodList as method>
    /**
 	 * ${method.comment}。
 	 */
    @RequestMapping(value = "/${method.name}",method = RequestMethod.GET)
    public ResponseMap ${method.name}(@RequestBody RequestMap requestMap) {
        try {
            return ${className}Service.${method.name}(requestMap);
        } catch (BizException e){
            logger.error("业务异常：",e);
        } catch (Exception e){
            logger.error("系统异常：",e);
        }
        return null;
    }
</#list>