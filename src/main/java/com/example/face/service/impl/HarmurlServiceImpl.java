package com.example.face.service.impl;

import com.example.face.entity.Harmurl;
import com.example.face.dao.HarmurlDao;
import com.example.face.service.HarmurlService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Harmurl)表服务实现类
 *
 * @author makejava
 * @since 2020-04-27 22:00:46
 */
@Service
public class HarmurlServiceImpl implements HarmurlService {
    @Resource
    private HarmurlDao harmurlDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Harmurl queryById(Integer id) {
        return this.harmurlDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Harmurl> queryAllByLimit(int offset, int limit) {
        return this.harmurlDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param harmurl 实例对象
     * @return 实例对象
     */
    @Override
    public Harmurl insert(Harmurl harmurl) {
        this.harmurlDao.insert(harmurl);
        return harmurl;
    }

    /**
     * 修改数据
     *
     * @param harmurl 实例对象
     * @return 实例对象
     */
    @Override
    public Harmurl update(Harmurl harmurl) {
        this.harmurlDao.update(harmurl);
        return this.queryById(harmurl.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.harmurlDao.deleteById(id) > 0;
    }
}