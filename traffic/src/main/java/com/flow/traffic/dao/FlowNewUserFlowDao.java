package com.flow.traffic.dao;


import com.flow.traffic.entity.*;
import com.flow.traffic.util.Page;

import java.util.List;

public interface FlowNewUserFlowDao {
    //用户流量统计图按柱行图统计
    List<UserFlowT> selectUserFlowT(QueryCondition con);
    //按折线图统计
    List<UserFlowT> selectUserFlowTZ(QueryCondition con);
    //用户流量统计表
    Page<UserFlowB> selectUserFlowB(Params params);
    //用户流量统计表
    Page<UserFlowB> selectUserFlowBZ(Params params);
    //用户流量统计的数据条数
    long selectUserFlowBnum(QueryCondition con);
    //弹出框上行流量
    List<UserFlowPopup> selectUserFlowPopupUp(QueryCondition con);
    //弹出框下行流量
    List<UserFlowPopup> selectUserFlowPopupDown(QueryCondition con);
    //弹出框连接数
    List<UserFlowPopup> selectUserFlowPopupLinks(QueryCondition con);
    //弹出框连接数
    Page<UserFlowProtocol> selectUserFlowPopupProtocol(Params params);
    //弹出框统计的数据条数
    long selectUserFlowPopupProtocolnum(QueryCondition con);
    //获取右上角的未备案Ip和活跃Ip
    List<DataNum> selectUserDataNum(QueryCondition con);
}
