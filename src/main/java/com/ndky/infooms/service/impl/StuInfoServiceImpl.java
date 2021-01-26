package com.ndky.infooms.service.impl;

import com.ndky.infooms.entity.StuInfo;
import com.ndky.infooms.mapper.StuInfoMapper;
import com.ndky.infooms.service.StuInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenqingsheng
 * @since 2021-01-20
 */
@Service
public class StuInfoServiceImpl extends ServiceImpl<StuInfoMapper, StuInfo> implements StuInfoService {

    /**
     * 增加一条学生信息
     *
     * @param stuInfo 对象
     * @return
     */
    @Override
    public int insertStuInfo(StuInfo stuInfo) {
        return baseMapper.insert(stuInfo);
    }

    /**
     * 更新一条学生信息
     *
     * @param stuInfo 对象
     * @return
     */
    @Override
    public int updataStuInfo(StuInfo stuInfo) {
        return baseMapper.updateById(stuInfo);
    }

    /**
     * 删除一条学生信息
     *
     * @param stuNum 学号
     * @return
     */
    @Override
    public int deleteStuInfo(Long stuNum) {
        return baseMapper.deleteById(stuNum);
    }

    /**
     * 根据学号删除一条学生信息
     *
     * @param stuNum 对象
     * @return
     */
    @Override
    public StuInfo selectStuInfo(Long stuNum) {
        return baseMapper.selectById(stuNum);
    }
}
