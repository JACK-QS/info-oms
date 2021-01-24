package com.ndky.infooms.config.Handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author chenqingsheng
 * @date 2021/1/21 10:19
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {


    /** 使用如果实现添加的操作，这个方法执行(属性名称，值，元数据对象)
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("ctime",new Date(),metaObject);
        this.setFieldValByName("utime",new Date(),metaObject);
        this.setFieldValByName("isDeleted", 0, metaObject);
    }

    /** 实现一个修改的操作，这个方法执行
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("utime",new Date(),metaObject);
    }

}
