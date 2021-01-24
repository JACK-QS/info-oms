package com.ndky.infooms.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenqingsheng
 * @since 2021-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class StuInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生学号
     */
    @TableId(value = "hea_id", type = IdType.INPUT)
    private Long stuNum;

    /**
     * 学生姓名
     */
    private String stuName;

    /**
     * 学生年龄
     */
    private String stuAge;

    /**
     * 学生性别
     */
    private String stuSex;

    /**
     * 学生民族
     */
    private String stuNation;

    /**
     * 学生电话
     */
    private String stuTelephone;

    /**
     * 学生生源地
     */
    private String stuOriginPlace;

    /**
     * 学生学科部
     */
    private String stuDepartment;

    /**
     * 学生专业
     */
    private String stuSpecialities;

    /**
     * 学生班级
     */
    private String stuClass;

    /**
     * 学生辅导员
     */
    private String stuInstructor;

    /**
     * 学生父亲名
     */
    private String stuFatherName;

    /**
     * 学生父亲电话
     */
    private String stuFatherNum;

    /**
     * 学生母亲名
     */
    private String stuMotherName;

    /**
     * 学生母亲电话
     */
    private String stuMotherNum;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date ctime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date utime;

    /**
     * 逻辑删除：1-删除、0-未删除
     */
    @TableLogic
    private Integer isDeleted;


}
