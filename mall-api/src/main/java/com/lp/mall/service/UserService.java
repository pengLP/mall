package com.lp.mall.service;


import com.lp.mall.bean.UmsMember;
import com.lp.mall.bean.UmsMemberReceiveAddress;

import java.util.List;

public interface UserService {


    List<UmsMember> getAllUser();

    List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId);


}
