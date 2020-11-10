package com.tengda.dazahui.system.dao;

import com.tengda.dazahui.system.domian.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author teswell
 */
@Mapper
public interface MomentMapper  {
    List<User> shuju();

}
