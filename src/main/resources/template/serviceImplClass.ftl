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

	/**
 	 * 查询列表。
 	 */
	public List<${className?cap_first}Vo> listByParam(String param) {

        // 数据查询：
        List<${className?cap_first}Bo> boList = ${className}Dao.listByParam(param);
        if (boList == null || boList.isEmpty()){
            return new ArrayList();
        }

        // 数据转换：
        List<${className?cap_first}Vo> voList = new ArrayList();
        for (${className?cap_first}Bo bo : boList){
            voList.add(${className}Dtf.to${className?cap_first}Vo(bo));
        }

    	return voList;
    }

    /**
     * 查询明细。
     */
    public ${className?cap_first}Bo selectByParam(String param) {
    	return ${className}Dao.selectByParam(param);
    }

    /**
     * 查询明细。
     */
    public ${className?cap_first}Bo selectById(Integer id) {
    	return ${className}Dao.selectById(id);
    }

    /**
     * 插入数据。
     */
    public int insert(${className?cap_first}Bo bo) {
    	return ${className}Dao.insert(bo);
    }

    /**
     * 修改数据。
     */
    public int update(${className?cap_first}Bo bo) {
    	return ${className}Dao.update(bo);
    }

    /**
     * 删除数据。（逻辑删除）
     */
    public int delete(${className?cap_first}Bo bo) {
    	return ${className}Dao.delete(bo);
    }
}
