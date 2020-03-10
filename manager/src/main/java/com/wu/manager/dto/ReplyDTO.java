package com.wu.manager.dto;

import com.wu.manager.pojo.Jie;
import com.wu.manager.pojo.User;
import lombok.Data;

/**
 * @program: iosbbs
 * @description: 回复数据传输类
 * @author: Wu
 * @create: 2020-03-10 09:31
 **/

@Data
public class ReplyDTO {

    private Integer id;

    private String content;

    private Long gmtCreate;

    private Jie parentId;

    private Long gmtModify;

    private Integer type;

    private Integer likeCount;

    private User creator;

    private Integer isAccept;
}
