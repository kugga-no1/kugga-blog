package com.li.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.li.pojo.Menu;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO  {
    @ApiModelProperty(value = "//后台菜单id")
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "菜单名")
    private String menuName;

    @ApiModelProperty(value = "跳转URI")
    private String uri;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "菜单上级id")
    private Integer parentId;

    @ApiModelProperty(value = "是否可用 1可用 0禁用")
    private Boolean disabled;

    private List<MenuDTO> MenuChildrens;

    public void addMenu(Menu menu){
        this.id=menu.getId();
        this.menuName=menu.getMenuName();
        this.uri=menu.getUri();
        this.icon=menu.getUri();
        this.parentId=menu.getParentId();
        this.disabled=menu.getDisabled();
        List<MenuDTO> newmenudto=new ArrayList<>();
        this.MenuChildrens=newmenudto;
    }

}
