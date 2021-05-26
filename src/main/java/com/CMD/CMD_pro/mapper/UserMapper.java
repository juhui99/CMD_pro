package com.CMD.CMD_pro.mapper;

import com.CMD.CMD_pro.domain.userVO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    public void userJoin(userVO user) throws Exception;
    public int idChk(String userID) throws Exception;
}
