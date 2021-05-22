package com.li.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author kugga
 * @since 2021-05-15
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("tb_menu")
@ApiModel(value="Menu对象", description="")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "菜单名")
    @TableField("MENU_NAME")
    private String menuName;

    @ApiModelProperty(value = "跳转URI")
    @TableField("URI")
    private String uri;

    @ApiModelProperty(value = "菜单图标")
    @TableField("ICON")
    private String icon;

    @ApiModelProperty(value = "菜单上级id")
    @TableField("PARENT_ID")
    private Integer parentId;

    @ApiModelProperty(value = "是否可用 1可用 0禁用")
    @TableField("DISABLED")
    private Boolean disabled;


}
