package ${packageName}.controller;

/**
 * ${classDesc}（控制层）
 * @author yufan
 */
@RestController
@RequestMapping("${className}")
public class ${className?cap_first}Controller {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private I${className?cap_first}Service ${className}Service;

    @RequestMapping(value = "/testJdbc",method = RequestMethod.GET)
    int testJdbc() {
        try {
            return demoService.testJdbc();
        } catch (Exception e){
            logger.error("",e);
        }
        return 0;
    }

    @RequestMapping("/testMybatis")
    List<ApiVo> testMybatis() {
    try {
		logger.info("测试Mybatis开始了.....");
    	return demoService.testMybatis();
    } catch (Exception e){
    	logger.error("",e);
    	return new ArrayList<>();
    }
}

    }
