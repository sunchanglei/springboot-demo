package com.boot.dao;

import com.boot.utils.JsonUtil;
import com.dfire.soa.common.RedisConstants;
import com.twodfire.redis.ICacheService;
import com.twodfire.util.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class CommDao {

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    /* 数据库操作类型 **/
    private static final String INSERT = "insert";
    private static final String INSERT_SELECTIVE = "insertSelective";
    private static final String UPDATE_BY_KEY = "updateByPrimaryKey";
    private static final String UPDATE_SELECTIVE = "updateByPrimaryKeySelective";
    private static final String SELECT_BY_PRM_KEY = "selectByPrimaryKey";
    private static final String DELETE_BY_PRM_KEY = "deleteByPrimaryKey";

    @Autowired
    protected ICacheService cacheService;

    /**
     * 获取MapperXML路径
     *
     * @param clazz 类
     * @param opt   操作
     * @return MapperXML路径
     */
    private String getNameSpacePath(Class clazz, String opt) {
        return clazz.getName().replace(".bo.", ".mapper.") + "Mapper." + opt;
    }

    /**
     * 删除REDIS值
     *
     * @param record 数据
     * @throws Exception
     */
    protected void delRedisKey(Object record) throws Exception {
        if (record == null) {
            return;
        }
        if (record instanceof String) {
            delRedisKey((String) record);
            return;
        }
        String json = JsonUtil.beanToJson(record);
        JsonNode node = JsonUtil.complexJson(json);
        String id = node.findValue("id").getTextValue();
        cacheService.del(RedisConstants.REDIS_KEY + id);
    }

    /**
     * 删除REDIS值
     *
     * @param id ID
     */
    protected void delRedisKey(String id) {
        cacheService.del(RedisConstants.REDIS_KEY + id);
    }

    /**
     * 删除REDIS值
     *
     * @param idList ID列表
     */
    protected void delRedisKeys(List<String> idList) {
        if (idList != null && idList.size() > 0) {
            for (String id : idList) {
                delRedisKey(id);
            }
        }
    }

    /**
     * 添加REDISkey值
     *
     * @param record 数据
     * @param id     KEY
     */
    private void addRedisKey(Object record, String id) {
        try {
            String json = JsonUtil.beanToJson(record);
            cacheService.set(RedisConstants.REDIS_KEY + id, json, 24 * 60 * 60);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        if (id == null || id.isEmpty()) {
            return null;
        }

        try {
            String json = cacheService.get(RedisConstants.REDIS_KEY + id);
            T rd;

            if (json == null) {
                rd = sqlSessionTemplate.selectOne(getNameSpacePath(clazz, SELECT_BY_PRM_KEY), id);
                if (rd != null) addRedisKey(rd, id);
            } else {
                rd = (T) JsonUtil.jsonToBean(json, clazz);
            }
            return rd;
        } catch (Exception e) {
            return null;
        }
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
        int rows = sqlSessionTemplate.update(getNameSpacePath(record.getClass(), UPDATE_BY_KEY), record);
        if (rows > 0) {
            delRedisKey(record);
        }
        return rows;
    }

    /**
     * 根据KEY更新记录
     *
     * @param record 更新的数据
     * @return 更新成功的件数
     * @throws Exception 系统异常
     */
    public int updateByPrimaryKeySelective(Object record) throws Exception {
        int rows = sqlSessionTemplate.update(getNameSpacePath(record.getClass(), UPDATE_SELECTIVE), record);
        if (rows > 0) {
            delRedisKey(record);
        }
        return rows;
    }

    /**
     * 根据KEY删除记录
     *
     * @param clazz 删除数据的类型
     * @param id    删除数据的ID
     * @return 删除成功的件数
     */
    public int deleteByPrimaryKey(Class clazz, String id) {
        int rows = sqlSessionTemplate.delete(getNameSpacePath(clazz, DELETE_BY_PRM_KEY), id);
        if (rows > 0) {
            delRedisKey(id);
        }
        return rows;
    }

}