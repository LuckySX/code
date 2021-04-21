package com.flow.traffic.util;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * Author: lfh
 * Version: 1.0
 * Date: 2016/12/30
 * Description: 基于mybatis-spring的SqlSessionDaoSupport提供数据访问实现
 * Function List:
 */
public class BaseDao extends SqlSessionDaoSupport {
    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    protected <S> S getMapper(Class<S> clazz) {
        return getSqlSession().getMapper(clazz);
    }

    /**
     * 获取connection
     *
     * @return connection
     */
    public Connection getConnection() {
        SqlSessionTemplate st = (SqlSessionTemplate) getSqlSession();
        return SqlSessionUtils.getSqlSession(st.getSqlSessionFactory(), st.getExecutorType(),
                st.getPersistenceExceptionTranslator()).getConnection();
    }

    /**
     * 返回指定类型的List集合
     *
     * @param method 要执行的mapper中的id
     * @return list集合
     */
    public <T> List<T> selectList(String method) {
        return this.getSqlSession().selectList(method);
    }

    /**
     * 返回指定类型的List集合
     *
     * @param method 要执行的mapper中的id
     * @param entity 指定类型的数据
     * @return list集合
     */
    public <T> List<T> selectList(String method, Object entity) {
        return this.getSqlSession().selectList(method, entity);
    }

	/**
	 * 返回总数
	 * @param method
	 * @param entity
	 * @return
	 */
	public Long selectTotal2(String method,Object entity) {
		return this.getSqlSession().selectOne(method, entity);
	}
	
    /**
     * 分页查询列表
     *
     * @param method   要执行的mapper中的id
     * @param entity   指定类型的数据
     * @param pageNo   页数
     * @param pageSize 每页显示数量
     * @return list集合
     */
    public <T,E> List<T> selectList(String method, E entity, int pageNo, int pageSize) {
        int offset = pageSize * (pageNo - 1);
        List<T> list = this.getSqlSession().selectList(method, entity, new RowBounds(offset, pageSize));
        return list;
    }

    /**
     * 分页查询列表
     *
     * @param method   要执行的mapper中的id
     * @param pageNo   页数
     * @param pageSize 每页显示数量
     * @return list集合
     */
    public <T> List<T> selectList(String method, int pageNo, int pageSize) {
        return this.getSqlSession().selectList(method, new RowBounds(pageSize * (pageNo - 1), pageSize));
    }

    /**
     * 返回指定类型数据
     *
     * @param method 要执行的mapper中的id
     * @return 指定类型实体
     */
    public <T> T selectObject(String method) {
        return this.getSqlSession().selectOne(method);
    }

    /**
     * 返回指定类型数据
     *
     * @param method 要执行的mapper中的id
     * @param entity 指定类型的数据
     * @return 指定类型实体
     */
    public <T> T selectObject(String method, Object entity) {
        return this.getSqlSession().selectOne(method, entity);
    }

    /**
     * 根据条件查询，返回map键名键值的集合
     *
     * @param method 要执行的mapper中的id
     * @return list集合（map 键名键值的集合）
     */
    public <K, V> List<Map<K, V>> selectMap(String method) {
        return this.getSqlSession().selectList(method);
    }

    /**
     * 根据条件查询，返回map键名键值的集合
     *
     * @param method 要执行的mapper中的id
     * @param entity 条件对象
     * @return list集合（map 键名键值的集合）
     */
    public <K, V> List<Map<K, V>> selectMap(String method, Object entity) {
        return this.getSqlSession().selectList(method, entity);
    }

    /**
     * 根据条件查询，返回map键名键值的集合
     *
     * @param method   要执行的mapper中的id
     * @param entity   指定类型的数据
     * @param pageNo   页数
     * @param pageSize 每页显示数量
     * @return  list集合（map 键名键值的集合）
     */
    public <K, V> List<Map<K, V>> selectMap(String method, Object entity, int pageNo, int pageSize) {
        return this.getSqlSession().selectList(method, entity, new RowBounds(pageSize * (pageNo - 1), pageSize));
    }

    /**
     * 返回指定key的map
     *
     * @param statement
     * @param entity
     * @param mapKey
     * @return  map 键名键值的集合
     */
    public <K, V> Map<K, V> selectMap(String statement, Object entity, String mapKey) {
        return this.getSqlSession().selectMap(statement, entity, mapKey);
    }

    /**
     * 插入数据
     *
     * @param method 要执行的mapper中的id
     * @param entity 插入对象，当传入的entity为List时，批量插入
     * @return 执行影响行数 ，为0表示未执行成功 大于0表示成功
     */
    public int insertObject(String method, Object entity) {
        return this.getSqlSession().insert(method, entity);
    }

    /**
     * 批量插入
     *
     * @param method  要执行的mapper中的id
     * @param objList 对象列表
     * @return list集合
     */
    public <T> List<T> batchInsertObject(String method, List<T> objList) {
        SqlSessionTemplate st = (SqlSessionTemplate) getSqlSession();
        SqlSession sqlSession = null;
        try {
            sqlSession = st.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
            for (T t : objList) {
                sqlSession.insert(method, t);
            }
            sqlSession.commit();
            sqlSession.clearCache();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null)
                sqlSession.close();
        }
        return objList;
    }
    /**
     * 批量插入
     *
     * @param method  要执行的mapper中的id
     * @param objList 对象列表
     * @return list集合
     */
    public <T> Boolean batchUpdateObject(String method, List<T> objList) {
        SqlSessionTemplate st = (SqlSessionTemplate) getSqlSession();
        SqlSession sqlSession = null;
        Boolean flag = true;
        try {
            sqlSession = st.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
            for (T t : objList) {
                sqlSession.update(method, t);
            }
            sqlSession.commit();
            sqlSession.clearCache();
        } catch (Exception e) {
            flag = false;
        } finally {
            if (sqlSession != null)
                sqlSession.close();
        }
        return flag;
    }
    /**
     * 修改数据
     *
     * @param method 要执行的mapper中的id
     * @param entity 修改对象
     * @return 执行影响行数 ，为0表示未执行成功 大于0表示成功
     */
    public int updateObject(String method, Object entity) {
        return this.getSqlSession().update(method, entity);
    }

    /**
     * 删除数据
     *
     * @param method 要执行的mapper中的id
     * @param entity 删除对象条件
     * @return 执行影响行数 ，为0表示未执行成功 大于0表示成功
     */
    public int deleteObject(String method, Object entity) {
        return this.getSqlSession().delete(method, entity);
    }

    /**
     * 返回总数
     *
     * @param method 要执行的mapper中的id
     * @param entity 指定类型的数据
     * @return 查询指定类型的数据记录总数
     */
    public long selectTotal(String method, Object entity) {
        return this.getSqlSession().selectOne(method, entity);
    }

    public long selectTotal(String method) {
        return this.getSqlSession().selectOne(method);
    }
}
