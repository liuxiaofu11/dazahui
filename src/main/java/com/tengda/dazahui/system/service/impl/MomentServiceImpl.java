package com.tengda.dazahui.system.service.impl;

import com.tengda.dazahui.system.dao.MomentMapper;
import com.tengda.dazahui.system.domian.User;
import com.tengda.dazahui.system.service.MomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author teswell
 * @Date 2020/11/10 11:42
 * @function
 */
@Service
public class MomentServiceImpl implements MomentService {
    @Autowired(required = false)
    private MomentMapper momentMapper;

    @Override
    public List<User> shuju() {
        List<User> userList = momentMapper.shuju();
        return userList;
    }
}
