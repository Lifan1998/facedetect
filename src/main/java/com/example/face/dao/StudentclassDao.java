package com.example.face.dao;

import com.example.face.entity.Studentclass;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Studentclass)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-05 21:37:34
 */
public interface StudentclassDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Studentclass queryById(Integer id);

    List<Studentclass> queryByClassId(Integer classId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Studentclass> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param studentclass 实例对象
     * @return 对象列表
     */
    List<Studentclass> queryAll(Studentclass studentclass);

    /**
     * 新增数据
     *
     * @param studentclass 实例对象
     * @return 影响行数
     */
    int insert(Studentclass studentclass);

    /**
     * 修改数据
     *
     * @param studentclass 实例对象
     * @return 影响行数
     */
    int update(Studentclass studentclass);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}