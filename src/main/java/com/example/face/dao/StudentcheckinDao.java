package com.example.face.dao;

import com.example.face.entity.Studentcheckin;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

/**
 * (Studentcheckin)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-06 15:07:42
 */
public interface StudentcheckinDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Studentcheckin queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Studentcheckin> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param studentcheckin 实例对象
     * @return 对象列表
     */
    List<Studentcheckin> queryAll(Studentcheckin studentcheckin);

    List<Studentcheckin> queryByCheckInId(@Param("checkInId") int checkInId);
    Studentcheckin queryByCheckInIdAndStudentId(@Param("checkInId") int checkInId, @Param("studentId") int studentId);

    /**
     * 新增数据
     *
     * @param studentcheckin 实例对象
     * @return 影响行数
     */
    int insert(Studentcheckin studentcheckin);

    /**
     * 修改数据
     *
     * @param studentcheckin 实例对象
     * @return 影响行数
     */
    int update(Studentcheckin studentcheckin);

    int updateStatus(@Param("studentId") Integer studentId, @Param("checkInId") Integer checkInId, @Param("status") Integer status);

    int deleteStudentByCheckIn(@Param("studentId") Integer studentId, @Param("checkInId") Integer checkInId);


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}