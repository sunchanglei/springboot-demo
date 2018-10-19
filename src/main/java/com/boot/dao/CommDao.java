package com.boot.dao;

import com.boot.bo.ApiBo;
import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 必须要指定DataSource,否则会加载出错。
 *
 */
@Repository
public class CommDao {

    @Autowired
    private JdbcTemplate yhJdbcTemplate;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    /* 数据库操作类型 **/
    private static final String INSERT = "insert";
    private static final String INSERT_SELECTIVE = "insertSelective";
    private static final String UPDATE_BY_KEY = "updateByPrimaryKey";
    private static final String UPDATE_SELECTIVE = "updateByPrimaryKeySelective";
    private static final String SELECT_BY_PRM_KEY = "selectByPrimaryKey";
    private static final String DELETE_BY_PRM_KEY = "deleteByPrimaryKey";

    public static void main(String[] args) {
        System.out.println(ApiBo.class.getName());
        System.out.println(new CommDao().getNameSpacePath(ApiBo.class,"1"));
    }

    /**
     * 获取MapperXML路径
     *
     * @param clazz 类
     * @param opt   操作
     * @return MapperXML路径
     */
    public String getNameSpacePath(Class clazz, String opt) {
        String name = clazz.getName().replace(".bo.", ".mapper.");
        return StringUtils.removeEnd(name,"Bo") + "Mapper." + opt;
    }

    /**
     * 根据KEY查询记录
     *
     * @param clazz 对象的类型
     * @param id    ID
     * @return 对象
     */
    @SuppressWarnings("unchecked")
    public <T> T selectByPrimaryKey(Class<T> clazz, String id) {
     return sqlSessionTemplate.selectOne(getNameSpacePath(clazz, SELECT_BY_PRM_KEY), id);
    }

    /**
     * 添加记录
     *
     * @param record 添加的数据
     * @return 添加成功的件数
     */
    public int insert(Object record) {
        return sqlSessionTemplate.insert(getNameSpacePath(record.getClass(), INSERT), record);
    }

    /**
     * 添加记录
     *
     * @param record 添加的数据
     * @return 添加成功的件数
     */
    public int insertSelective(Object record) {
        return sqlSessionTemplate.insert(getNameSpacePath(record.getClass(), INSERT_SELECTIVE), record);
    }

    /**
     * 根据KEY更新记录
     *
     * @param record 更新的数据
     * @return 更新成功的件数
     * @throws Exception 系统异常
     */
    public int updateByPrimaryKey(Object record) throws Exception {
        return sqlSessionTemplate.update(getNameSpacePath(record.getClass(), UPDATE_BY_KEY), record);
    }

    /**
     * 根据KEY更新记录
     *
     * @param record 更新的数据
     * @return 更新成功的件数
     * @throws Exception 系统异常
     */
    public int updateByPrimaryKeySelective(Object record) throws Exception {
        return sqlSessionTemplate.update(getNameSpacePath(record.getClass(), UPDATE_SELECTIVE), record);
    }

    /**
     * 根据KEY删除记录
     *
     * @param clazz 删除数据的类型
     * @param id    删除数据的ID
     * @return 删除成功的件数
     */
    public int deleteByPrimaryKey(Class clazz, String id) {
        return sqlSessionTemplate.delete(getNameSpacePath(clazz, DELETE_BY_PRM_KEY), id);
    }

}