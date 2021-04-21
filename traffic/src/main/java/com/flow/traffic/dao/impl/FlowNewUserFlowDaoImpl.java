package com.flow.traffic.dao.impl;


import com.flow.traffic.dao.FlowNewUserFlowDao;
import com.flow.traffic.entity.*;
import com.flow.traffic.util.BaseDao;
import com.flow.traffic.util.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FlowNewUserFlowDaoImpl extends BaseDao implements FlowNewUserFlowDao {
    private static final String GBASE_KEY = "gbase";
    private static final String MPP_KEY = "mpp";
    @Override
    public List<UserFlowT> selectUserFlowT(QueryCondition con) {

        List<UserFlowT> list = this.selectList("com.chanct.flowDirection.userMapper.selectUserT", con);
        return list;
    }
    @Override
    public List<UserFlowT> selectUserFlowTZ(QueryCondition con) {

        List<UserFlowT> list = this.selectList("com.chanct.flowDirection.userMapper.selectUserTZ", con);
        return list;
    }
    @Override
    public Page<UserFlowB> selectUserFlowB(Params param) {
        Page<UserFlowB> page = new Page<>(param);

        page.setRows(this.selectList("com.chanct.flowDirection.userMapper.selectUserB",param, param.getPage(), param.getRows()));
        page.setTotal(this.selectTotal("com.chanct.flowDirection.userMapper.selectUserBnum", param));
        return page;
    }
    @Override
    public Page<UserFlowB> selectUserFlowBZ(Params param) {
        Page<UserFlowB> page = new Page<>(param);
        page.setRows(this.selectList("com.chanct.flowDirection.userMapper.selectUserBZ",param, param.getPage(), param.getRows()));
        page.setTotal(this.selectTotal("com.chanct.flowDirection.userMapper.selectUserBnum", param));
        return page;
    }
    @Override
    public List<UserFlowPopup> selectUserFlowPopupUp(QueryCondition con) {

        List<UserFlowPopup> list = this.selectList("com.chanct.flowDirection.userMapper.selectUserPopupUp", con);
        return list;
    }
    @Override
    public List<UserFlowPopup> selectUserFlowPopupDown(QueryCondition con) {

        List<UserFlowPopup> list = this.selectList("com.chanct.flowDirection.userMapper.selectUserPopupDown", con);
        return list;
    }
    @Override
    public List<UserFlowPopup> selectUserFlowPopupLinks(QueryCondition con) {

        List<UserFlowPopup> list = this.selectList("com.chanct.flowDirection.userMapper.selectUserPopupLinks", con);
        return list;
    }
    @Override
    public Page<UserFlowProtocol> selectUserFlowPopupProtocol(Params param) {

        Page<UserFlowProtocol> page = new Page<>(param);
        page.setRows(this.selectList("com.chanct.flowDirection.userMapper.selectUserFlowProtocol",param, param.getPage(), param.getRows()));
        page.setTotal(this.selectTotal("com.chanct.flowDirection.userMapper.selectUserFlowProtocolnum", param));

        return page;
    }
    @Override
    public long selectUserFlowBnum(QueryCondition con) {

        long i = this.selectTotal("com.chanct.flowDirection.userMapper.selectUserBnum", con);
        return i;
    }
    @Override
    public long selectUserFlowPopupProtocolnum(QueryCondition con) {

        long i = this.selectTotal("com.chanct.flowDirection.userMapper.selectUserFlowProtocolnum", con);
        return i;
    }
    @Override
    public List<DataNum> selectUserDataNum(QueryCondition con) {

        List<DataNum> list = this.selectList("com.chanct.flowDirection.userMapper.selectUserDataNum", con);
        return list;
    }

}


