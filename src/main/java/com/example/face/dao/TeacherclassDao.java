package com.example.face.dao;

import com.example.face.entity.Teacherclass;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Teacherclass)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-05 21:33:57
 */
public interface TeacherclassDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Teacherclass queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Teacherclass> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param teacherclass 实例对象
     * @return 对象列表
     */
    List<Teacherclass> queryAll(Teacherclass teacherclass);

    List<Teacherclass> queryByTeacherId(Integer teacherId);

    /**
     * 新增数据
     *
     * @param teacherclass 实例对象
     * @return 影响行数
     */
    int insert(Teacherclass teacherclass);

    /**
     * 修改数据
     *
     * @param teacherclass 实例对象
     * @return 影响行数
     */
    int update(Teacherclass teacherclass);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}