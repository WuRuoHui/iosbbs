package com.wu.manager.service.impl;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.mapper.DeptMapper;
import com.wu.manager.pojo.Dept;
import com.wu.manager.pojo.DeptExample;
import com.wu.manager.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: iosbbs
 * @description: 部门service实现类
 * @author: Wu
 * @create: 2020-01-29 22:36
 **/

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public LayUIResult insertOrUpdateDept(Dept dept) {
        System.out.println(dept);
        if (dept == null) {
            return LayUIResult.build(1,"部门信息不能为空");
        }
        DeptExample deptExample = new DeptExample();
        DeptExample.Criteria criteria = deptExample.createCriteria().andNameEqualTo(dept.getName().trim());
        if (dept.getId() != null) {
            criteria.andIdNotEqualTo(dept.getId());
        }
        List<Dept> depts = deptMapper.selectByExample(deptExample);
        if (depts != null && depts.size() > 0) return LayUIResult.fail("操作失败，部门已存在");
        //如果id不为空，则进行更新操作
        if (dept.getId() != null) {
            int rows = deptMapper.updateByPrimaryKeySelective(dept);
            if (rows > 0) {
                return LayUIResult.build(0,"更新成功");
            }
            return LayUIResult.fail("更新失败，请稍后再试");
        }
        int rows = deptMapper.insert(dept);
        if (rows > 0 ) {
            return LayUIResult.build(0,"添加成功");
        }
        return LayUIResult.fail("添加失败");
    }

    @Override
    public LayUIResult selectDeptList() {
        DeptExample deptExample = new DeptExample();
        List<Dept> depts = deptMapper.selectByExample(deptExample);
        if (depts != null && depts.size() > 0) {
            return LayUIResult.build(0,depts.size(),"success",depts);
        }
        return LayUIResult.build(1,"无数据");
    }

    @Override
    public LayUIResult deleteDeptById(Integer id) {
        int rows = deptMapper.deleteByPrimaryKey(id);
        if (rows > 0 ) {
            return LayUIResult.build(0,"删除成功！");
        }
        return LayUIResult.build(1,"您要删除的数据不存在！");
    }
}
