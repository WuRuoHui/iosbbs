package com.wu.manager.service.impl;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.mapper.FriendlyLinkMapper;
import com.wu.manager.pojo.FriendlyLink;
import com.wu.manager.pojo.FriendlyLinkExample;
import com.wu.manager.service.SystemSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Override
    public LayUIResult getAllLinkList() {
        FriendlyLinkExample friendlyLinkExample = new FriendlyLinkExample();
        List<FriendlyLink> friendlyLinks = friendlyLinkMapper.selectByExample(friendlyLinkExample);
        Integer count = 0;
        if (friendlyLinks != null || friendlyLinks.size()>0) {
            count = friendlyLinks.size();
        }
        return LayUIResult.build(0,count,"success",friendlyLinks);
    }

    @Override
    public LayUIResult addLinkList(FriendlyLink friendlyLink) {
        if (friendlyLink == null) {
            return LayUIResult.build(1,"请补全友链信息");
        }
        if (StringUtils.isEmpty(friendlyLink.getWebsiteName())) {
            return LayUIResult.build(1,"网站名必填");
        }
        if (StringUtils.isEmpty(friendlyLink.getWebsiteUrl())) {
            return LayUIResult.build(1,"网站链接必填");
        }
        //判断是否存在linkId，存在即说明是修改请求
        if (friendlyLink.getLinkId()!=null) {
            friendlyLink.setModifyTime(System.currentTimeMillis());
            int i = friendlyLinkMapper.updateByPrimaryKeySelective(friendlyLink);
            if (i<1) {
                return LayUIResult.build(1,"修改失败");
            }
            return LayUIResult.build(0,"修改成功");
        }
        //查询此次插入的友链是否存在，根据网站名和网站URL判断
        FriendlyLinkExample friendlyLinkExample = new FriendlyLinkExample();
        FriendlyLinkExample.Criteria criteria1 = friendlyLinkExample.createCriteria();
        criteria1.andWebsiteNameEqualTo(friendlyLink.getWebsiteName());
        FriendlyLinkExample.Criteria criteria2 = friendlyLinkExample.createCriteria();
        criteria2.andWebsiteUrlEqualTo(friendlyLink.getWebsiteUrl());
        friendlyLinkExample.or(criteria2);
        List<FriendlyLink> friendlyLinks = friendlyLinkMapper.selectByExample(friendlyLinkExample);
        //如果存在返回添加失败的提示信息
        if (friendlyLinks != null && friendlyLinks.size() >0) {
            return LayUIResult.build(1,"友链已存在");
        }
        //设置插入时间、修改时间毫秒值
        friendlyLink.setAddTime(System.currentTimeMillis());
        friendlyLink.setModifyTime(System.currentTimeMillis());
        int insert = friendlyLinkMapper.insert(friendlyLink);
        if (insert<1) {
            return LayUIResult.build(1,"添加失败");
        }else {
            return LayUIResult.build(0,"添加成功");
        }
    }

    @Override
    public LayUIResult deleteLinkListById(Integer linkId) {
        if (linkId == null) {
            return LayUIResult.build(1,"删除失败");
        }
        int i = friendlyLinkMapper.deleteByPrimaryKey(linkId);
        if (i<1) {
            return LayUIResult.build(1,"删除失败");
        }
        return LayUIResult.build(0,"删除成功");
    }
}
