package com.lp.mall.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lp.mall.bean.UmsMember;
import com.lp.mall.bean.UmsMemberReceiveAddress;
import com.lp.mall.service.UserService;
import com.lp.mall.user.mapper.UmsMemberReceiverAddressMapper;
import com.lp.mall.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UmsMemberReceiverAddressMapper umsMemberReceiverAddressMapper;

    @Override
    public List<UmsMember> getAllUser() {
        List<UmsMember> userMembers = userMapper.selectAll();
        return userMembers;
    }

    @Override
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId) {
        // 封装的参数对象
        UmsMemberReceiveAddress umsMemberReceiveAddress = new UmsMemberReceiveAddress();
        umsMemberReceiveAddress.setMemberId(memberId);
        List<UmsMemberReceiveAddress> umsMemberReceiveAddresses = umsMemberReceiverAddressMapper.select(umsMemberReceiveAddress);



        return umsMemberReceiveAddresses;
    }

}
