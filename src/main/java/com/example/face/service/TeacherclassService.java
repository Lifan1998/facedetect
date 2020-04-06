package com.example.face.service;

import com.example.face.entity.Teacherclass;
import java.util.List;

/**
 * (Teacherclass)表服务接口
 *
 * @author makejava
 * @since 2020-04-05 21:33:57
 */
public interface TeacherclassService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Teacherclass queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Teacherclass> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param teacherclass 实例对象
     * @return 实例对象
     */
    Teacherclass insert(Teacherclass teacherclass);

    /**
     * 修改数据
     *
     * @param teacherclass 实例对象
     * @return 实例对象
     */
    Teacherclass update(Teacherclass teacherclass);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}