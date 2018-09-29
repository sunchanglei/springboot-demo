package ${packageName}.service;

public interface I${className?cap_first}Service {

	/**
 	 * 查询列表。
 	 */
	List<${className?cap_first}Vo> listByParam(String param);

    /**
     * 查询明细。
     */
    ${className?cap_first}Vo selectByParam(String param);

    /**
     * 查询明细。
     */
    ${className?cap_first}Vo selectById(Integer id);

    /**
     * 插入数据。
     */
    int insert(${className?cap_first}Vo vo);

    /**
     * 修改数据。
     */
    int update(${className?cap_first}Vo vo);

    /**
     * 删除数据。（逻辑删除）
     */
    int delete(${className?cap_first}Vo vo);

}
