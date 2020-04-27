package com.example.face.controller;

import com.example.face.dao.HarmurlDao;
import com.example.face.entity.Harmurl;
import com.example.face.service.HarmurlService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Harmurl)表控制层
 *
 * @author makejava
 * @since 2020-04-27 22:00:46
 */
@RestController
@RequestMapping("harmurl")
public class HarmurlController {
    /**
     * 服务对象
     */
    @Resource
    private HarmurlService harmurlService;

    @Resource
    private HarmurlDao harmurlDao;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Harmurl selectOne(Integer id) {
        return this.harmurlService.queryById(id);
    }

    @GetMapping("/isHarmUrl")
    public Boolean isHarmUrl(@Param("url") String url) {
        List<Harmurl> harmurls = harmurlDao.queryByDomain(url);

        if (harmurls.isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

}