package com.example.roc.dao;

import com.example.roc.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RocDao {
    @Insert("insert into juejin_order(order_num,create_time) values(#{orderNum},NOW())")
    int insert(Order order);

    @Select("select * from juejin_order where order_num = #{orderId}")
    Order selectById(String orderId);

    @Select("select * from juejin_order order by create_time desc")
    List<Order> orderList();

    @Update("update juejin_order set status = 2,update_time = NOW() where order_num = #{orderNum} and status = 0")
    void updOrder(String orderNum);
}
