package com.example.face.service.impl;

import com.example.face.entity.Studentclass;
import com.example.face.dao.StudentclassDao;
import com.example.face.service.StudentclassService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Studentclass)表服务实现类
 *
 * @author makejava
 * @since 2020-04-05 21:37:36
 */
@Service("studentclassService")
public class StudentclassServiceImpl implements StudentclassService {
    @Resource
    private StudentclassDao studentclassDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Studentclass queryById(Integer id) {
        return this.studentclassDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Studentclass> queryAllByLimit(int offset, int limit) {
        return this.studentclassDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param studentclass 实例对象
     * @return 实例对象
     */
    @Override
    public Studentclass insert(Studentclass studentclass) {
        this.studentclassDao.insert(studentclass);
        return studentclass;
    }

    /**
     * 修改数据
     *
     * @param studentclass 实例对象
     * @return 实例对象
     */
    @Override
    public Studentclass update(Studentclass studentclass) {
        this.studentclassDao.update(studentclass);
        return this.queryById(studentclass.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.studentclassDao.deleteById(id) > 0;
    }
}