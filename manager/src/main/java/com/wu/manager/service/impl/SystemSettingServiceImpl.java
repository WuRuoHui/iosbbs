package com.wu.manager.service.impl;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.mapper.FriendlyLinkMapper;
import com.wu.manager.pojo.FriendlyLink;
import com.wu.manager.pojo.FriendlyLinkExample;
import com.wu.manager.service.SystemSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @description: 系统设置service实现类
 * @author: Wu
 * @create: 2020-01-08 09:19
 **/
@Service
public class SystemSettingServiceImpl implements SystemSettingService {

    @Autowired
    private FriendlyLinkMapper friendlyLinkMapper;

    /**
     * @Description: 删除单个友链
     * @Param: [linkId]
     * @return: com.wu.common.utils.LayUIResult
     * @Author: wu
     * @Date: 2020/1/10
     */
    @Override
    @Transactional
    public LayUIResult deleteLinkListById(Integer linkId) {
        if (linkId == null) {
            return LayUIResult.build(1, "删除失败");
        }
        int i = friendlyLinkMapper.deleteByPrimaryKey(linkId);
        if (i < 1) {
            return LayUIResult.build(1, "删除失败");
        }
        return LayUIResult.build(0, "删除成功");
    }

    /**
     * @Description: 获得所有友链
     * @Param: []
     * @return: com.wu.common.utils.LayUIResult
     * @Author: wu
     * @Date: 2020/1/10
     * @param search
     */
    @Override
    public LayUIResult getAllLinkList(String search) {
        FriendlyLinkExample friendlyLinkExample = new FriendlyLinkExample();
        if (!StringUtils.isEmpty(search)) {
            friendlyLinkExample.createCriteria().andWebsiteNameLike('%'+search.trim()+'%');
        }
        List<FriendlyLink> friendlyLinks = friendlyLinkMapper.selectByExample(friendlyLinkExample);
        Integer count = 0;
        if (friendlyLinks != null || friendlyLinks.size() > 0) {
            count = friendlyLinks.size();
        }
        return LayUIResult.build(0, count, "success", friendlyLinks);
    }

    /**
     * @Description: 插入友链
     * @Param: [friendlyLink]
     * @return: com.wu.common.utils.LayUIResult
     * @Author: wu
     * @Date: 2020/1/10
     */
    @Override
    @Transactional
    public LayUIResult addLinkList(FriendlyLink friendlyLink) {
        if (friendlyLink == null) {
            return LayUIResult.build(1, "请补全友链信息");
        }
        //判断网站名是否为空
        if (StringUtils.isEmpty(friendlyLink.getWebsiteName())) {
            return LayUIResult.build(1, "网站名必填");
        }
        //判断网站链接是否为空
        if (StringUtils.isEmpty(friendlyLink.getWebsiteUrl())) {
            return LayUIResult.build(1, "网站链接必填");
        }
        //查询此次插入的友链是否存在，根据网站名和网站URL判断
        FriendlyLinkExample friendlyLinkExample = new FriendlyLinkExample();
        FriendlyLinkExample.Criteria criteria1 = friendlyLinkExample.createCriteria();
        criteria1.andWebsiteNameEqualTo(friendlyLink.getWebsiteName());
        FriendlyLinkExample.Criteria criteria2 = friendlyLinkExample.createCriteria();
        criteria2.andWebsiteUrlEqualTo(friendlyLink.getWebsiteUrl());
        //当属于修改请求时，不与自身进行比较
        if (friendlyLink.getLinkId() != null) {
            criteria1.andLinkIdNotEqualTo(friendlyLink.getLinkId());
            criteria2.andLinkIdNotEqualTo(friendlyLink.getLinkId());
        }
        friendlyLinkExample.or(criteria2);
        List<FriendlyLink> friendlyLinks = friendlyLinkMapper.selectByExample(friendlyLinkExample);
        //如果存在返回添加失败的提示信息
        if (friendlyLinks != null && friendlyLinks.size() > 0) {
            return LayUIResult.build(1, "友链已存在");
        }
        //判断是否存在linkId，存在即说明是修改请求
        if (friendlyLink.getLinkId() != null) {
            friendlyLink.setModifyTime(System.currentTimeMillis());
            int i = friendlyLinkMapper.updateByPrimaryKeySelective(friendlyLink);
            if (i < 1) {
                return LayUIResult.build(1, "修改失败");
            }
            return LayUIResult.build(0, "修改成功");
        }
        //设置插入时间、修改时间毫秒值
        friendlyLink.setAddTime(System.currentTimeMillis());
        friendlyLink.setModifyTime(System.currentTimeMillis());
        int insert = friendlyLinkMapper.insert(friendlyLink);
        if (insert < 1) {
            return LayUIResult.build(1, "添加失败");
        } else {
            return LayUIResult.build(0, "添加成功");
        }
    }

    /**
     * @Description:批量删除友链
     * @Param: [linkIds]
     * @return: com.wu.common.utils.LayUIResult
     * @Author: wu
     * @Date: 2020/1/10
     */
    @Override
    @Transactional
    public LayUIResult deleteLinkListByIds(List<Integer> linkIds) {
        if (linkIds != null && linkIds.size() > 0) {
            for (Integer i : linkIds) {
                int row = friendlyLinkMapper.deleteByPrimaryKey(i);
                if (row < 1) return LayUIResult.build(1,"删除失败");
            }
            return LayUIResult.build(0,"删除成功");
        } else {
            return LayUIResult.build(1,"删除失败");
        }
    }
}
