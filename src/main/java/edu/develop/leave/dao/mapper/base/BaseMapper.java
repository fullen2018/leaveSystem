package edu.develop.leave.dao.mapper.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *mapper的基本方法
 *
 * @anthor zsl
 * @date 2018/08/19
 *
 */
public interface BaseMapper<E> {
    /**
     * 根据id删除数据
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(@Param("id") Integer id);

    /**
     * 添加数据
     *
     * @param e
     * @return
     */
    int insertSelective(E e);

    /**
     * 根据id更新整条数据
     *
     * @param e
     * @return
     */
    int updateByPrimaryKey(E e);

    /**
     * 根据id更新对应的字段
     *
     * @param e
     * @param
     * @return
     */
    int updateByPrimaryKeySelective(E e);



    /**
     * 根据条件查询数据
     *
     * @param offset 开始查询的数据下标
     * @param limit 读出的数据条数
     * @param conditions 查询的条件
     * @return
     */
    List<E> queryByCondition(@Param("offset") Integer offset, @Param("limit") Integer limit, @Param("conditions") String conditions);

    /**
     * 根据id查询数据
     *
     * @param id
     * @return
     */
    E selectByPrimaryKey(@Param("id") Integer id);


    /**
     * 根据条件查询数据条数
     *
     * @param conditions
     * @return
     */
    Long queryAmount(@Param("conditions") String conditions);

    /**
     * 批量删除数据
     * @param ids
     * @return
     */
    Integer deletes(@Param("ids") Integer[] ids);

}
