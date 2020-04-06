package com.example.face.service;

import com.example.face.entity.Classroom;
import java.util.List;

/**
 * (Classroom)表服务接口
 *
 * @author makejava
 * @since 2020-03-26 21:45:35
 */
public interface ClassroomService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Classroom queryById(Object id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Classroom> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param classroom 实例对象
     * @return 实例对象
     */
    Classroom insert(Classroom classroom);

    /**
     * 修改数据
     *
     * @param classroom 实例对象
     * @return 实例对象
     */
    Classroom update(Classroom classroom);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}