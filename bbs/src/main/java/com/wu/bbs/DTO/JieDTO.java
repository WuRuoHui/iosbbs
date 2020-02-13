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

    public String getColumn(Integer columnId) {

        if (columnId == 0) {
            return "提问";
        }
        if (columnId == 99) {
            return "分享";
        }
        if (columnId == 100) {
            return "讨论";
        }
        if (columnId == 101) {
            return "建议";
        }
        if (columnId == 168) {
            return "公告";
        }
        if (columnId == 169) {
            return "动态";
        }
        return "";
    }
}
