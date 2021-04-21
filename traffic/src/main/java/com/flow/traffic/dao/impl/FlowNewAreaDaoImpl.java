package com.flow.traffic.dao.impl;

import com.flow.traffic.dao.FlowNewAreaDao;
import com.flow.traffic.entity.AreaStatisticsGraph;
import com.flow.traffic.entity.AreaStatisticsTableIp;
import com.flow.traffic.entity.AreaStatisticsTableWeb;
import com.flow.traffic.entity.QueryCondition;
import com.flow.traffic.util.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FlowNewAreaDaoImpl extends BaseDao implements FlowNewAreaDao {


	/*@Override
	public List<AreaStatisticsGraph> selectTareaDay(QueryCondition con) {
		System.out.println("进入dao");
		List<AreaStatisticsGraph> list = this.selectList("com.chanct.flowintroduce.selectAreaDay", con);
		return list;
	}*/

    public List<AreaStatisticsGraph> selectTareaHour(QueryCondition con, String index) {
        List<AreaStatisticsGraph> list = new ArrayList<AreaStatisticsGraph>();
        if(index.equals("0")){
            list = this.selectList("com.chanct.flowNewAreaMapper.selectAreaWeb", con);
        }else if(index.equals("1")){
            list = this.selectList("com.chanct.flowNewAreaMapper.selectWeb", con);
        }else if(index.equals("2")){
            list = this.selectList("com.chanct.flowNewAreaMapper.selectAreaIp", con);
        }else if(index.equals("3")){
            list = this.selectList("com.chanct.flowNewAreaMapper.selectIp", con);
        }
        return list;

    }

    public List<AreaStatisticsTableWeb> selectBareaForWeb(QueryCondition con) {
        List<AreaStatisticsTableWeb> list = this.selectList("com.chanct.flowNewAreaMapper.selectAreaTableForWeb", con);
        return list;
    }

    public List<AreaStatisticsTableIp> selectBareaForIp(QueryCondition con) {

        List<AreaStatisticsTableIp> list = this.selectList("com.chanct.flowNewAreaMapper.selectAreaTableForIp", con);
        return list;

    }

    @Override
    public String selectMaxTime(QueryCondition con) {
        return this.selectObject("com.chanct.flowNewAreaMapper.selectMaxTime", con);
    }


    public long selectBareaForWebnum(QueryCondition con) {
        long i = this.selectTotal("com.chanct.flowNewAreaMapper.selectAreaTableForWebnum", con);
        return i;
    }


}

