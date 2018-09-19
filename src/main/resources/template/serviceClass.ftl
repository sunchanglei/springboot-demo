package ${packageName}.service;

public interface I${className?cap_first}Service {

	/**
 	 * 查询列表。
 	 */
	public List<${className?cap_first}Bo> listByParam(String param);

    /**
     * 查询明细。
     */
    public ${className?cap_first}Bo selectByParam(String param);

    /**
     * 查询明细。
     */
    public ${className?cap_first}Bo selectById(Integer id);

    /**
     * 插入数据。
     */
    public int insert(${className?cap_first}Bo bo);

    /**
     * 修改数据。
     */
    public int update(${className?cap_first}Bo bo);

    /**
     * 删除数据。（逻辑删除）
     */
    public int delete(${className?cap_first}Bo bo);

}
