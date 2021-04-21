package com.flow.traffic.service;


import java.util.List;
import java.util.Map;

public interface InterFlowService{

    List map(Map map);

    List worldMap(Map map);

    Map totalInfo(Map map);

    List userTop(Map map);

    List protocolTop(Map map);
}