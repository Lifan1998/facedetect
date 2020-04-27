package com.example.face.dao;

import com.example.face.entity.Harmurl;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Harmurl)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-27 22:00:46
 */
public interface HarmurlDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Harmurl queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Harmurl> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param harmurl 实例对象
     * @return 对象列表
     */
    List<Harmurl> queryAll(Harmurl harmurl);

    /**
     * 新增数据
     *
     * @param harmurl 实例对象
     * @return 影响行数
     */
    int insert(Harmurl harmurl);

    /**
     * 修改数据
     *
     * @param harmurl 实例对象
     * @return 影响行数
     */
    int update(Harmurl harmurl);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<Harmurl> queryByDomain(String url);

}