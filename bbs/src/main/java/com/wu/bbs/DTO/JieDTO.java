package com.wu.bbs.DTO;

import lombok.Data;
import lombok.ToString;

/**
 * @program: iosbbs
 * @description: 求解数据传输类
 * @author: Wu
 * @create: 2020-02-13 10:14
 **/

@Data
@ToString
public class JieDTO {

    private Integer id;

    private String title;

    private Integer columnId;

    private Integer projectId;

    private Long gmtCreate;

    private Long gmtModify;

    private UserSimpleDTO creator;

    private Integer viewCount;

    private Integer likeCount;

    private Integer commentCount;

    private Boolean isSticky;

    private Boolean isBoutique;

    private Boolean isClosed;

    private String content;

}
