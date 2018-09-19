package ${packageName}.dao;

/**
 * ${classDesc}（数据层）
 */
@Repository
public class ${className?cap_first}Dao {

	@Autowired
    private ${className?cap_first}Mapper ${className}Mapper;

	/**
 	 * 查询列表。
 	 */
	public List<${className?cap_first}Bo> listByParam(String param) {
		return ${className}Mapper.listByParam(param);
	}

    /**
     * 查询明细。
     */
    public ${className?cap_first}Bo selectByParam(String param) {
		return ${className}Mapper.selectByParam(param);
	}

    /**
     * 查询明细。
     */
    public ${className?cap_first}Bo selectById(Integer id) {
    	return ${className}Mapper.selectByPrimaryKey(id);
    }

	/**
	 * 插入数据。
	 */
    public int insert(${className?cap_first}Bo bo) {
    	return ${className}Mapper.insertSelective(bo);
	}

    /**
     * 修改数据。
     */
    public int update(${className?cap_first}Bo bo) {
    	return ${className}Mapper.updateByPrimaryKeySelective(bo);
	}

    /**
     * 删除数据。（逻辑删除）
     */
    public int delete(${className?cap_first}Bo bo) {
    	return ${className}Mapper.updateByPrimaryKeySelective(bo);
	}

}
