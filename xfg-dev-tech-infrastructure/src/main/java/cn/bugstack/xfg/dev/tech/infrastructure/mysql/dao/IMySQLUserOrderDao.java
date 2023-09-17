package cn.bugstack.xfg.dev.tech.infrastructure.mysql.dao;

import cn.bugstack.xfg.dev.tech.infrastructure.mysql.po.UserOrderPO;

import java.util.List;

public interface IMySQLUserOrderDao {

    void insert(UserOrderPO userOrderPO);

    void updateOrderStatusByUserId(String userId);

    void updateOrderStatusByUserMobile(String userMobile);

    void updateOrderStatusByOrderId(String orderId);

    UserOrderPO selectById(Long id);

    List<UserOrderPO> selectByUserId(String userId);

    List<UserOrderPO> selectByUserMobile(String userMobile);

    UserOrderPO selectByOrderId(String orderId);

    UserOrderPO selectByOrderIdAndUserId(UserOrderPO userOrderPO);

    UserOrderPO selectByUserIdAndOrderId(UserOrderPO userOrderPO);

    Long queryMaxId();

}
