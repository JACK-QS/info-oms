package com.ndky.infooms.vo.menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenqingsheng
 * @date 2021/1/21 11:43
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuNameVO {

    private Long id;

    private String menuName;
}
