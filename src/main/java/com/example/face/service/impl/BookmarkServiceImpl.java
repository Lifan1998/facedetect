package com.example.face.service.impl;

import com.example.face.entity.Bookmark;
import com.example.face.dao.BookmarkDao;
import com.example.face.service.BookmarkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Bookmark)表服务实现类
 *
 * @author makejava
 * @since 2020-05-07 19:53:19
 */
@Service
public class BookmarkServiceImpl implements BookmarkService {
    @Resource
    private BookmarkDao bookmarkDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Bookmark queryById(Integer id) {
        return this.bookmarkDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Bookmark> queryAllByLimit(int offset, int limit) {
        return this.bookmarkDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param bookmark 实例对象
     * @return 实例对象
     */
    @Override
    public Bookmark insert(Bookmark bookmark) {
        this.bookmarkDao.insert(bookmark);
        return bookmark;
    }

    /**
     * 修改数据
     *
     * @param bookmark 实例对象
     * @return 实例对象
     */
    @Override
    public Bookmark update(Bookmark bookmark) {
        this.bookmarkDao.update(bookmark);
        return this.queryById(bookmark.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.bookmarkDao.deleteById(id) > 0;
    }
}