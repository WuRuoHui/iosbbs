package com.wu.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.wu.common.enums.CustomizeErrorCode;
import com.wu.common.utils.LayUIResult;
import com.wu.manager.dto.ReplyDTO;
import com.wu.manager.mapper.JieMapper;
import com.wu.manager.mapper.ReplyMapper;
import com.wu.manager.mapper.UserMapper;
import com.wu.manager.pojo.Jie;
import com.wu.manager.pojo.Reply;
import com.wu.manager.pojo.ReplyExample;
import com.wu.manager.pojo.User;
import com.wu.manager.service.ReplyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: iosbbs
 * @description: 回复服务实现类
 * @author: Wu
 * @create: 2020-03-10 09:11
 **/

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JieMapper jieMapper;

    @Override
    public LayUIResult selectReplys(Integer page, Integer limit) {
        if (page != null && limit != null){
            PageHelper.startPage(page,limit);
        }
        List<Reply> replies = replyMapper.selectByExample(new ReplyExample());
        List<ReplyDTO> replyDTOS = new ArrayList<>();
        if (replies != null && replies.size() > 0) {
            for (Reply reply: replies) {
                ReplyDTO replyDTO = new ReplyDTO();
                BeanUtils.copyProperties(reply,replyDTO);
                if (reply.getParentId() !=null) {
                    Jie jie = jieMapper.selectByPrimaryKey(reply.getParentId());
                    replyDTO.setParentId(jie);
                }
                if (reply.getCreator() != null) {
                    User user = userMapper.selectByPrimaryKey(reply.getCreator());
                    user.setPassword(null);
                    replyDTO.setCreator(user);
                }
                replyDTOS.add(replyDTO);
            }
        }
        long count = replyMapper.countByExample(new ReplyExample());
        return LayUIResult.build(0,(int)count, CustomizeErrorCode.SELECT_DATA_SUCCESS.getMessage(),replyDTOS);
    }

    @Override
    @Transactional
    public LayUIResult deleteReplyById(Integer id) {
        if (id == null) {
            return LayUIResult.build(1,CustomizeErrorCode.NOT_ROW_SELECT.getMessage());
        }
        Reply reply = replyMapper.selectByPrimaryKey(id);
        if (reply == null) {
            return LayUIResult.build(1,CustomizeErrorCode.DATA_NOT_FOUND.getMessage());
        }
        int rows = replyMapper.deleteByPrimaryKey(id);
        if (rows > 0 ) {
            Jie jie = jieMapper.selectByPrimaryKey(reply.getParentId());
            Jie newJie = new Jie();
            jie.setId(jie.getId());
            jie.setCommentCount(jie.getCommentCount() -1);
            if (jie != null) {
                jieMapper.updateByPrimaryKeySelective(jie);
            }
            return LayUIResult.build(0,CustomizeErrorCode.DELETE_DATA_SUCCESS.getMessage());
        }
        return LayUIResult.build(1,CustomizeErrorCode.DELETE_DATA_FAIL.getMessage());
    }
}
