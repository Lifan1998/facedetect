package com.example.face.dao;

import com.example.face.entity.Checkin;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Checkin)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-06 15:44:23
 */
public interface CheckinDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Checkin queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Checkin> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    List<Checkin> queryAllByLimitOrderByAddTime(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param checkin 实例对象
     * @return 对象列表
     */
    List<Checkin> queryAll(Checkin checkin);

    /**
     * 新增数据
     *
     * @param checkin 实例对象
     * @return 影响行数
     */
    int insert(Checkin checkin);

    /**
     * 修改数据
     *
     * @param checkin 实例对象
     * @return 影响行数
     */
    int update(Checkin checkin);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}