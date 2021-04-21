package com.flow.traffic.dao;

import java.util.List;
import java.util.Map;

public interface InterFlowDao{

    List map(Map map);

    Map totalInfo(Map map);

    List protocolTop(Map map);

    List userTop(Map map);

    List worldMap(Map map);
}