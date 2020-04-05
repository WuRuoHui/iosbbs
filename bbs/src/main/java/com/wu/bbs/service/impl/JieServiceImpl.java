package com.wu.bbs.service.impl;

import com.github.pagehelper.PageHelper;
import com.wu.bbs.DTO.JieDTO;
import com.wu.bbs.DTO.ReplyDTO;
import com.wu.bbs.DTO.UserDTO;
import com.wu.bbs.DTO.UserSimpleDTO;
import com.wu.bbs.mapper.JieMapper;
import com.wu.bbs.mapper.ReplyMapper;
import com.wu.bbs.mapper.UserGradeMapper;
import com.wu.bbs.mapper.UserMapper;
import com.wu.bbs.pojo.*;
import com.wu.bbs.service.JieService;
import com.wu.common.enums.CustomizeErrorCode;
import com.wu.common.enums.impl.CustomizeJieTypeCode;
import com.wu.common.utils.LayUIResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: iosbbs
 * @description: 求解service实现类
 * @author: Wu
 * @create: 2020-01-24 14:50
 **/

@Service
public class JieServiceImpl implements JieService {

    @Autowired
    private JieMapper jieMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserGradeMapper userGradeMapper;
    @Value("${BBS_INDEX_JIE_STICKY_SIZE}")
    private Integer BBS_INDEX_JIE_STICKY_SIZE;
    @Value("${BBS_INDEX_JIE_NOT_STICKY_SIZE}")
    private Integer BBS_INDEX_JIE_NOT_STICKY_SIZE;
    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public LayUIResult insertOrUpdate(Jie jie, Authentication authentication) {
        if (authentication == null) return LayUIResult.build(1,"未登录无法发布，请先登录");
        User user = (User) authentication.getPrincipal();
        jie.setCreator(user.getId());
        jie.setGmtCreate(System.currentTimeMillis());
        jie.setGmtModify(System.currentTimeMillis());
        int rows = jieMapper.insertSelective(jie);
        if (rows > 0) {
            return LayUIResult.build(0,"发布成功");
        }
        return LayUIResult.build(1,"发布失败");
    }

    @Override
    public List<JieDTO> selectAllJieListWithoutStick() {
//        List<JieDTO> jieDTOS = new ArrayList<>();
        PageHelper.startPage(1,BBS_INDEX_JIE_NOT_STICKY_SIZE);
        JieExample jieExample = new JieExample();
        jieExample.createCriteria()
                .andIsStickyEqualTo(false);
        jieExample.setOrderByClause("gmt_create DESC");
        /*List<Jie> jies = jieMapper.selectByExample(jieExample);
        if (jies != null && jies.size() > 0) {
            for (Jie jie : jies) {
                JieDTO jieDTO = new JieDTO();
                BeanUtils.copyProperties(jie,jieDTO);
                if (jie.getCreator() != null) {
                    User user = userMapper.selectByPrimaryKey(jie.getCreator());
                    if (user != null) {
                        UserSimpleDTO userSimpleDTO = new UserSimpleDTO();
                        BeanUtils.copyProperties(user,userSimpleDTO);
                        //设置用户VIP等级
                        if (!StringUtils.isEmpty(user.getVipLevel())){
                            UserGrade userGrade = userGradeMapper.selectByPrimaryKey(Integer.valueOf(user.getVipLevel()));
                            userSimpleDTO.setUserGrade(userGrade);
                        }
                        //设置求解创建者
                        jieDTO.setCreator(userSimpleDTO);
                    }
                }
                jieDTOS.add(jieDTO);
            }
        }*/
        List<JieDTO> jieDTOS = selectJieList(jieExample);
        return jieDTOS;
    }

    public List<JieDTO> selectJieList(JieExample jieExample) {
        List<Jie> jies = jieMapper.selectByExample(jieExample);
        List<JieDTO> jieDTOS = copyJieToJieDTO(jies);
        return jieDTOS;
    }

    //将jie封装到jieDTO
    public List<JieDTO> copyJieToJieDTO(List<Jie> jies) {
        List<JieDTO> jieDTOS = new ArrayList<>();
        if (jies != null && jies.size() > 0) {
            for (Jie jie : jies) {
                JieDTO jieDTO = new JieDTO();
                BeanUtils.copyProperties(jie, jieDTO);
                if (jie.getCreator() != null) {
                    User user = userMapper.selectByPrimaryKey(jie.getCreator());
                    if (user != null) {
                        UserSimpleDTO userSimpleDTO = new UserSimpleDTO();
                        BeanUtils.copyProperties(user, userSimpleDTO);
                        //设置用户VIP等级
                        if (!StringUtils.isEmpty(user.getVipLevel())) {
                            UserGrade userGrade = userGradeMapper.selectByPrimaryKey(Integer.valueOf(user.getVipLevel()));
                            userSimpleDTO.setUserGrade(userGrade);
                        }
                        //设置求解创建者
                        jieDTO.setCreator(userSimpleDTO);
                    }
                }
                jieDTOS.add(jieDTO);
            }
        }
        return jieDTOS;
    }

    @Override
    public JieDTO selectJieById(Integer jieId) {
        Jie jie = jieMapper.selectByPrimaryKey(jieId);
        JieDTO jieDTO = new JieDTO();
        BeanUtils.copyProperties(jie,jieDTO);
        if (jie.getCreator() != null) {
            User user = userMapper.selectByPrimaryKey(jie.getCreator());
            if (user != null) {
                UserSimpleDTO userSimpleDTO = new UserSimpleDTO();
                BeanUtils.copyProperties(user,userSimpleDTO);
                //设置用户VIP等级
                if (!StringUtils.isEmpty(user.getVipLevel())){
                    UserGrade userGrade = userGradeMapper.selectByPrimaryKey(Integer.valueOf(user.getVipLevel()));
                    userSimpleDTO.setUserGrade(userGrade);
                }
                //设置求解创建者
                jieDTO.setCreator(userSimpleDTO);
            }
        }
        return jieDTO;
    }

    @Override
    public LayUIResult deleteJieById(Integer jieId) {
        if (jieId == null) {
            return LayUIResult.build(1,CustomizeErrorCode.DELETE_DATA_FAIL.getMessage());
        }
        Jie jie = jieMapper.selectByPrimaryKey(jieId);
        if (jie == null) {
            return LayUIResult.build(1, CustomizeErrorCode.DATA_NOT_FOUND.getMessage());
        }
        int rows = jieMapper.deleteByPrimaryKey(jieId);
        if (rows > 0 ) {
            return LayUIResult.build(0,CustomizeErrorCode.DELETE_DATA_SUCCESS.getMessage());
        }
        return LayUIResult.build(1,CustomizeErrorCode.DELETE_DATA_FAIL.getMessage());
    }

    @Override
    public LayUIResult updateJie(Jie jie) {
        System.out.println(jie);
        if (jie == null || jie.getId() == null) {
            return LayUIResult.build(1,CustomizeErrorCode.UPDATE_DATA_FAIL.getMessage());
        }
        Jie isExist = jieMapper.selectByPrimaryKey(jie.getId());
        if (isExist == null) {
            return LayUIResult.build(1,CustomizeErrorCode.DATA_NOT_FOUND.getMessage());
        }
        //设置修改时间
        jie.setGmtModify(System.currentTimeMillis());
        int rows = jieMapper.updateByPrimaryKeySelective(jie);
        if (rows > 0 ) {
            return LayUIResult.build(0,CustomizeErrorCode.UPDATE_DATA_SUCCESS.getMessage());
        }
        return LayUIResult.build(1,CustomizeErrorCode.UPDATE_DATA_FAIL.getMessage());
    }

    @Override
    public LayUIResult updateJieStickById(Integer jieId, Boolean rank) {
        if (jieId != null && rank != null ) {
            Jie jie = jieMapper.selectByPrimaryKey(jieId);
            if (jie == null) {
                return LayUIResult.build(1,CustomizeErrorCode.DATA_NOT_FOUND.getMessage());
            }
            Jie newJie = new Jie();
            newJie.setId(jieId);
            newJie.setIsSticky(rank);
            newJie.setGmtModify(System.currentTimeMillis());
            int rows = jieMapper.updateByPrimaryKeySelective(newJie);
            if (rows > 0 ) {
                return LayUIResult.build(0, CustomizeErrorCode.UPDATE_DATA_SUCCESS.getMessage());
            }
        }
        return LayUIResult.build(1,CustomizeErrorCode.UPDATE_DATA_FAIL.getMessage());
    }

    @Override
    public LayUIResult updateJieBoutiqueById(Integer jieId, Boolean rank) {
        if (jieId != null && rank != null ) {
            Jie jie = jieMapper.selectByPrimaryKey(jieId);
            if (jie == null) {
                return LayUIResult.build(1,CustomizeErrorCode.DATA_NOT_FOUND.getMessage());
            }
            Jie newJie = new Jie();
            newJie.setId(jieId);
            newJie.setIsBoutique(rank);
            newJie.setGmtModify(System.currentTimeMillis());
            int rows = jieMapper.updateByPrimaryKeySelective(newJie);
            if (rows > 0 ) {
                return LayUIResult.build(0, CustomizeErrorCode.UPDATE_DATA_SUCCESS.getMessage());
            }
        }
        return LayUIResult.build(1,CustomizeErrorCode.UPDATE_DATA_FAIL.getMessage());
    }

    @Override
    public List<JieDTO> selectAllJieListIfStick() {
        PageHelper.startPage(1,BBS_INDEX_JIE_STICKY_SIZE);
        JieExample jieExample = new JieExample();
        jieExample.createCriteria()
                .andIsStickyEqualTo(true);
        jieExample.setOrderByClause("gmt_create DESC");
        List<JieDTO> jieDTOS = selectJieList(jieExample);
        return jieDTOS;
    }

    List<JieDTO> selectJieByCondition(Integer columnId) {
        JieExample jieExample = new JieExample();
        jieExample.createCriteria().andColumnIdEqualTo(columnId);
        List<Jie> jies = jieMapper.selectByExample(jieExample);
        List<JieDTO> jieDTOS = copyJieToJieDTO(jies);
        return jieDTOS;
    }

    /**
     * @Description: 根据columnId和当前页码查询jie集合
     * @Param: [columnId, curr]
     * @return: java.util.List<com.wu.bbs.DTO.JieDTO>
     * @Date: 2020/3/1
     */
    List<JieDTO> selectJieByCondition(Integer columnId,Integer curr) {
        PageHelper.startPage(curr,10);
        JieExample jieExample = new JieExample();
        jieExample.setOrderByClause("gmt_create DESC");
        jieExample.createCriteria().andColumnIdEqualTo(columnId);
        List<Jie> jies = jieMapper.selectByExample(jieExample);
        List<JieDTO> jieDTOS = copyJieToJieDTO(jies);
        return jieDTOS;
    }

    /**
     * @Description: 根据columnId计算总记录数
     * @Param: [columnId]
     * @return: java.lang.Integer
     * @Date: 2020/3/1
     */
    @Override
    public Integer countJieByColumnId(Integer columnId) {
        JieExample jieExample = new JieExample();
        jieExample.createCriteria()
                .andColumnIdEqualTo(columnId);
        long count = jieMapper.countByExample(jieExample);
        return (int)count;
    }

    @Override
    public List<JieDTO> selectJieByCurr(Integer curr) {
        PageHelper.startPage(curr,10);
        JieExample jieExample = new JieExample();
        jieExample.setOrderByClause("gmt_create DESC");
        List<Jie> jies = jieMapper.selectByExample(jieExample);
        List<JieDTO> jieDTOS = copyJieToJieDTO(jies);
        return jieDTOS;
    }

    @Override
    public Integer countJie() {
        long count = jieMapper.countByExample(new JieExample());
        return (int)count;
    }

    @Override
    public List<JieDTO> selectJieByStatusAndCurr(String status, Integer curr) {
        isRightStatus(status);
        PageHelper.startPage(curr,10);
        JieExample jieExample = new JieExample();
        JieExample.Criteria criteria = jieExample.createCriteria();
        if (!StringUtils.isEmpty(status)) {
            if (status.equals("unsolved")) {
                criteria.andIsClosedEqualTo(false);
            }
            if (status.equals("solved")) {
                criteria.andIsClosedEqualTo(true);
            }
            if (status.equals("boutique")) {
                criteria.andIsBoutiqueEqualTo(true);
            }
        }
        jieExample.setOrderByClause("gmt_create DESC");
        List<Jie> jies = jieMapper.selectByExample(jieExample);
        List<JieDTO> jieDTOS = copyJieToJieDTO(jies);
        return jieDTOS;
    }

    @Override
    public Integer countJieByStatus(String status) {
        isRightStatus(status);
        JieExample jieExample = new JieExample();
        JieExample.Criteria criteria = jieExample.createCriteria();
        if (!StringUtils.isEmpty(status)) {
            if (status.equals("unsolved")) {
                criteria.andIsClosedEqualTo(false);
            }
            if (status.equals("solved")) {
                criteria.andIsClosedEqualTo(true);
            }
            if (status.equals("boutique")) {
                criteria.andIsBoutiqueEqualTo(true);
            }
        }
        long count = jieMapper.countByExample(jieExample);
        return (int)count;
    }

    @Override
    public List<Jie> selectQuizJieByCreator(Integer id) {
        PageHelper.startPage(1,10);
        JieExample jieExample = new JieExample();
        jieExample.createCriteria()
                .andColumnIdEqualTo(CustomizeJieTypeCode.JIE_JIE.getCode())
                .andCreatorEqualTo(id);
        List<Jie> jies = jieMapper.selectByExample(jieExample);
        return jies;
    }

    @Override
    public List<Jie> selectJieByUserId(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        JieExample jieExample = new JieExample();
        jieExample.createCriteria()
                .andCreatorEqualTo(user.getId());
        List<Jie> jies = jieMapper.selectByExample(jieExample);
        return jies;
    }

    @Override

    public LayUIResult insertReply(Integer jid, String content, Authentication authentication) {
        if (jid == null || StringUtils.isEmpty(content)) {
            return LayUIResult.build(1,CustomizeErrorCode.INSERT_DATA_NOT_FILL.getMessage());
        }
        User user = (User) authentication.getPrincipal();
        Reply reply = new Reply();
        reply.setCreator(user.getId());
        reply.setContent(content);
        reply.setGmtCreate(System.currentTimeMillis());
        reply.setGmtModify(System.currentTimeMillis());
        reply.setParentId(jid);
        replyMapper.insertSelective(reply);
        Jie jie = jieMapper.selectByPrimaryKey(jid);
        jie.setCommentCount(jie.getCommentCount()+1);
        jieMapper.updateByPrimaryKeySelective(jie);
        return LayUIResult.build(0,CustomizeErrorCode.REPLY_SUCCESS.getMessage());
    }

    @Override
    public List<ReplyDTO> selectJieReply(Integer jieId) {
        ReplyExample replyExample = new ReplyExample();
        replyExample.createCriteria()
                .andParentIdEqualTo(jieId);
        List<Reply> replies = replyMapper.selectByExample(replyExample);
        List<ReplyDTO> replyDTOS = new ArrayList<>();
        for (Reply reply :replies) {
            ReplyDTO replyDTO = new ReplyDTO();
            BeanUtils.copyProperties(reply,replyDTO);
            User user = userMapper.selectByPrimaryKey(reply.getCreator());
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user,userDTO);
            UserGrade userGrade = userGradeMapper.selectByPrimaryKey(user.getVipLevel());
            userDTO.setUserGrade(userGrade);
            replyDTO.setCreator(userDTO);
            replyDTOS.add(replyDTO);
        }
        return replyDTOS;
    }

    @Override
    public LayUIResult deleteReplyById(Integer id) {
        if (id == null) {
            return LayUIResult.build(1,CustomizeErrorCode.DELETE_DATA_FAIL.getMessage());
        }
        Reply reply = replyMapper.selectByPrimaryKey(id);
        if (reply == null) {
            return LayUIResult.build(1,CustomizeErrorCode.DATA_NOT_FOUND.getMessage());
        }
        int rows = replyMapper.deleteByPrimaryKey(id);
        if (rows > 0) {
            Jie jie = jieMapper.selectByPrimaryKey(reply.getParentId());
            Jie newJie = new Jie();
            newJie.setId(jie.getId());
            newJie.setCommentCount(jie.getCommentCount()-1);
            jieMapper.updateByPrimaryKeySelective(newJie);
            return LayUIResult.build(0,CustomizeErrorCode.DELETE_DATA_SUCCESS.getMessage());
        }
        return LayUIResult.build(1,CustomizeErrorCode.DELETE_DATA_FAIL.getMessage());
    }

    @Override
    public LayUIResult updateReply(Reply reply) {
        if (reply == null || reply.getId() == null) {
            return LayUIResult.build(1,CustomizeErrorCode.UPDATE_DATA_FAIL.getMessage());
        }
        Reply replyIsExist = replyMapper.selectByPrimaryKey(reply.getId());
        if (replyIsExist == null) {
            return LayUIResult.build(1,CustomizeErrorCode.DATA_NOT_FOUND.getMessage());
        }
        reply.setGmtModify(System.currentTimeMillis());
        int rows = replyMapper.updateByPrimaryKeySelective(reply);
        if (rows > 0) {
            return LayUIResult.build(0,CustomizeErrorCode.UPDATE_DATA_SUCCESS.getMessage());
        }
        return LayUIResult.build(1,CustomizeErrorCode.UPDATE_DATA_FAIL.getMessage());
    }

    @Override
    public List<JieDTO> selectJieByCurrAndSearch(Integer curr, String q) {
        PageHelper.startPage(curr,10);
        JieExample jieExample = new JieExample();
        if (q!=null && !StringUtils.isEmpty(q)) {
            jieExample.createCriteria()
                    .andTitleLike("%"+q.trim()+"%");
        }
        jieExample.setOrderByClause("gmt_create DESC");
        List<Jie> jies = jieMapper.selectByExample(jieExample);
        List<JieDTO> jieDTOS = copyJieToJieDTO(jies);
        return jieDTOS;
    }

    /**
     * @Description: 根据columnId和status查询对应的总记录数
     * @Param: [columnId, status]
     * @return: java.lang.Integer
     * @Date: 2020/3/1
     */
    @Override
    public Integer countJieByColumnIdAndStatus(Integer columnId, String status) {
        JieExample jieExample = new JieExample();
        JieExample.Criteria criteria = jieExample.createCriteria()
                .andColumnIdEqualTo(columnId);
        if (!StringUtils.isEmpty(status)) {
            if (status.equals("unsolved")) {
                criteria.andIsClosedEqualTo(false);
            }
            if (status.equals("solved")) {
                criteria.andIsClosedEqualTo(true);
            }
            if (status.equals("boutique")) {
                criteria.andIsBoutiqueEqualTo(true);
            }
        }
        long count = jieMapper.countByExample(jieExample);
        return (int)count;
    }

    @Override
    public List<JieDTO> selectJieByColumnIdAndCurr(Integer columnId, Integer curr) {
        List<JieDTO> jieDTOS = selectJieByCondition(columnId, curr);
        return jieDTOS;
    }

    @Override
    public List<JieDTO> selectJieByColumnIdAndStatusAndCurr(Integer columnId, String status, Integer curr) {
        isRightStatus(status);
        List<JieDTO> jieDTOS = selectJieByCondition(columnId, status, curr);
        return jieDTOS;
    }

    List<JieDTO> selectJieByCondition(Integer columnId,String status) {
        JieExample jieExample = new JieExample();
        JieExample.Criteria criteria = jieExample.createCriteria().andColumnIdEqualTo(columnId);
        if (!StringUtils.isEmpty(status)) {
            if (status.equals("unsolved")) {
                criteria.andIsClosedEqualTo(false);
            }
            if (status.equals("solved")) {
                criteria.andIsClosedEqualTo(true);
            }
            if (status.equals("boutique")) {
                criteria.andIsBoutiqueEqualTo(true);
            }
        }
        List<Jie> jies = jieMapper.selectByExample(jieExample);
        List<JieDTO> jieDTOS = copyJieToJieDTO(jies);
        return jieDTOS;
    }

    List<JieDTO> selectJieByCondition(Integer columnId,String status,Integer curr) {
        PageHelper.startPage(curr,10);
        JieExample jieExample = new JieExample();
        JieExample.Criteria criteria = jieExample.createCriteria().andColumnIdEqualTo(columnId);
        if (!StringUtils.isEmpty(status)) {
            if (status.equals("unsolved")) {
                criteria.andIsClosedEqualTo(false);
            }
            if (status.equals("solved")) {
                criteria.andIsClosedEqualTo(true);
            }
            if (status.equals("boutique")) {
                criteria.andIsBoutiqueEqualTo(true);
            }
        }
        jieExample.setOrderByClause("gmt_create DESC");
        List<Jie> jies = jieMapper.selectByExample(jieExample);
        List<JieDTO> jieDTOS = copyJieToJieDTO(jies);
        return jieDTOS;
    }

    public void isRightStatus(String status) {
        if (!status.equals("unsolved") || !status.equals("solved") || !status.equals("sticky")) {
            return;
        }
        throw new RuntimeException();
    }

    public Integer getIntegerType(String type) {
        /*<option value="0">提问</option>
        <option value="99">分享</option>
        <option value="100">讨论</option>
        <option value="101">建议</option>
        <option value="168">公告</option>
        <option value="169">动态</option>*/
        if (type.equals("jie")) {
            return 0;
        } else if (type.equals("discussion")){
            return 100;
        } else if (type.equals("share")) {
            return 99;
        } else if (type.equals("advice")) {
            return 101;
        } else if (type.equals("notice")) {
            return 168;
        } else if (type.equals("condition")) {
            return 169;
        }
        return null;
    }
}
