package com.ndky.infooms.service;

import com.ndky.infooms.entity.StuInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenqingsheng
 * @since 2021-01-20
 */
public interface StuInfoService extends IService<StuInfo> {

    /**
     * 增加一条学生信息
     * @param stuInfo 对象
     * @return
     */
    int insertStuInfo(StuInfo stuInfo);

    /**
     * 更新一条学生信息
     * @param stuInfo 对象
     * @return
     */
    int updataStuInfo(StuInfo stuInfo);

    /**
     * 删除一条学生信息
     * @param stuNum 学号
     * @return
     */
    int deleteStuInfo(Long stuNum);

    /**
     * 根据学号删除一条学生信息
     * @param stuNum 学号
     * @return
     */
    StuInfo selectStuInfo(Long stuNum);

}
