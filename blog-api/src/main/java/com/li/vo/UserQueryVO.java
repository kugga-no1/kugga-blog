package com.li.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryVO implements Serializable {
    public String roleName;
    public String nickname;
    public int current;  //页码
    public int size;  //一页几条

}
