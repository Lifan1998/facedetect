package com.example.face.service.impl;

import com.example.face.entity.Classroom;
import com.example.face.dao.ClassroomDao;
import com.example.face.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Classroom)表服务实现类
 *
 * @author makejava
 * @since 2020-03-26 21:45:36
 */
@Service("classroomService")
public class ClassroomServiceImpl implements ClassroomService {
    @Autowired
    private ClassroomDao classroomDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Classroom queryById(Object id) {
        return this.classroomDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Classroom> queryAllByLimit(int offset, int limit) {
        return this.classroomDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param classroom 实例对象
     * @return 实例对象
     */
    @Override
    public Classroom insert(Classroom classroom) {
        this.classroomDao.insert(classroom);
        return classroom;
    }

    /**
     * 修改数据
     *
     * @param classroom 实例对象
     * @return 实例对象
     */
    @Override
    public Classroom update(Classroom classroom) {
        this.classroomDao.update(classroom);
        return this.queryById(classroom.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Object id) {
        return this.classroomDao.deleteById(id) > 0;
    }
}