package com.example.face.controller;

import com.example.face.dao.BookmarkDao;
import com.example.face.entity.Bookmark;
import com.example.face.service.BookmarkService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Bookmark)表控制层
 *
 * @author makejava
 * @since 2020-05-07 19:53:19
 */
@RestController
@RequestMapping("/bookmark")
public class BookmarkController {
    /**
     * 服务对象
     */
    @Resource
    private BookmarkService bookmarkService;

    @Resource
    private BookmarkDao bookmarkDao;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/get")
    public Bookmark get(Integer id) {
        return this.bookmarkService.queryById(id);
    }

    @GetMapping("/list")
    public List<Bookmark> getUserBookmarks(@RequestParam Integer userId, @RequestParam Integer type) {
        return bookmarkDao.queryByUserId(userId, type);
    }

}