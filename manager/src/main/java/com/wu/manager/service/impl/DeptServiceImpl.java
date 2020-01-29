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
        return null;
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
}
