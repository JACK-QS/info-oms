package com.ndky.infooms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class StuHealthinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 体检表id
     */
    @TableId(value = "hea_id", type = IdType.AUTO)
    private Long heaId;

    /**
     * 五官：裸眼视力左
     */
    private String heaVisionLeft;

    /**
     * 五官：裸眼视力右
     */
    private String heaVisionRight;

    /**
     * 五官：矫正视力左
     */
    private String heaCorrectLeft;

    /**
     * 五官：矫正视力右
     */
    private String heaCorrectRight;

    /**
     * 五官：色觉检查
     */
    private String heaColor;

    /**
     * 五官：耳朵听力左
     */
    private String heaListenLeft;

    /**
     * 五官：耳朵听力右
     */
    private String heaListenRight;

    /**
     * 五官：耳疾
     */
    private String heaEars;

    /**
     * 五官：嗅觉
     */
    private String heaSmell;

    /**
     * 外科：身高
     */
    private String heaRise;

    /**
     * 外科：体重
     */
    private String heaWeight;

    /**
     * 外科：皮肤
     */
    private String heaSkin;

    /**
     * 外科：淋巴
     */
    private String heaLymph;

    /**
     * 外科：甲状腺
     */
    private String heaThroid;

    /**
     * 外科：脊柱
     */
    private String heaSpine;

    /**
     * 外科：四肢
     */
    private String heaLimb;

    /**
     * 外科：其它
     */
    private String heaSurgeryOther;

    /**
     * 内科：血压
     */
    private String heaBlood;

    /**
     * 内科：心率
     */
    private String heaIncumbent;

    /**
     * 内科：神经及精神
     */
    private String heaNerve;

    /**
     * 内科：肺及呼吸道
     */
    private String heaLung;

    /**
     * 内科：心脏及血管
     */
    private String heaHeart;

    /**
     * 内科：肝
     */
    private String heaLiver;

    /**
     * 内科：脾
     */
    private String heaSpleen;

    /**
     * 内科：其他
     */
    private String heaInternalOther;

    /**
     * 化验检查
     */
    private String heaChest;

    /**
     * 胸部放射线检查
     */
    private String heaConclusion;

    /**
     * 体检医院
     */
    private String heaHospital;

    /**
     * 体检结论
     */
    private String heaRemarks;

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


}
