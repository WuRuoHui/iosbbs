package com.wu.bbs.DTO;

import lombok.Data;

/**
 * @program: iosbbs
 * @description: 回复数据传输类
 * @author: Wu
 * @create: 2020-03-04 20:39
 **/

@Data
public class ReplyDTO {

    private Integer id;

    private String content;

    private Long gmtCreate;

    private Integer parentId;

    private Long gmtModify;

    private Integer type;

    private Integer likeCount;

    private UserDTO creator;

    private Integer isAccept;
}
