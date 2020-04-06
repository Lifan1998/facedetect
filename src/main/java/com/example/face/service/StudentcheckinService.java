package com.example.face.service;

import com.example.face.entity.Studentcheckin;
import java.util.List;

/**
 * (Studentcheckin)表服务接口
 *
 * @author makejava
 * @since 2020-04-05 22:06:54
 */
public interface StudentcheckinService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Studentcheckin queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Studentcheckin> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param studentcheckin 实例对象
     * @return 实例对象
     */
    Studentcheckin insert(Studentcheckin studentcheckin);

    /**
     * 修改数据
     *
     * @param studentcheckin 实例对象
     * @return 实例对象
     */
    Studentcheckin update(Studentcheckin studentcheckin);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}