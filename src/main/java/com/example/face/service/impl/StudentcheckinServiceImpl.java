package com.example.face.service.impl;

import com.example.face.entity.Studentcheckin;
import com.example.face.dao.StudentcheckinDao;
import com.example.face.service.StudentcheckinService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Studentcheckin)表服务实现类
 *
 * @author makejava
 * @since 2020-04-05 22:06:54
 */
@Service("studentcheckinService")
public class StudentcheckinServiceImpl implements StudentcheckinService {
    @Resource
    private StudentcheckinDao studentcheckinDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Studentcheckin queryById(Integer id) {
        return this.studentcheckinDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Studentcheckin> queryAllByLimit(int offset, int limit) {
        return this.studentcheckinDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param studentcheckin 实例对象
     * @return 实例对象
     */
    @Override
    public Studentcheckin insert(Studentcheckin studentcheckin) {
        this.studentcheckinDao.insert(studentcheckin);
        return studentcheckin;
    }

    /**
     * 修改数据
     *
     * @param studentcheckin 实例对象
     * @return 实例对象
     */
    @Override
    public Studentcheckin update(Studentcheckin studentcheckin) {
        this.studentcheckinDao.update(studentcheckin);
        return this.queryById(studentcheckin.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.studentcheckinDao.deleteById(id) > 0;
    }

    /**
     * 创建或更新某个学生的打卡状态
     *
     * @param studentId
     * @param checkInId
     * @param status
     * @return
     */
    @Override
    public int createOrUpdateStatus(int studentId, int checkInId, int status) {
        // 获取某个学生的状态
        Studentcheckin studentcheckin = studentcheckinDao.queryByCheckInIdAndStudentId(checkInId, studentId);
        if (studentcheckin == null) {
            studentcheckin = new Studentcheckin();
            studentcheckin.setCheckinId(checkInId);
            studentcheckin.setStudentId(studentId);
            studentcheckin.setStatus(status);
            return studentcheckinDao.insert(studentcheckin);
        } else {
            return studentcheckinDao.updateStatus(studentId, checkInId, status);
        }
    }
}