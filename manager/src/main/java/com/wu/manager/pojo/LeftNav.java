package com.wu.manager.pojo;

import lombok.Data;

@Data
public class LeftNav {
    private Integer id;

    private String title;

    private String icon;

    private String href;

    private Boolean spread;

    private Integer menuLevel;

    private Boolean isParent;

    private Integer parentId;

    private Boolean status;
}