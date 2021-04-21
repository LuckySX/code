package com.flow.traffic.entity;

import java.io.Serializable;
import java.util.List;

public class OperatorProtocolEntity implements Serializable {
    private List operators;
    private List protocols;

    public List getOperators() {
        return operators;
    }

    public void setOperators(List operators) {
        this.operators = operators;
    }

    public List getProtocols() {
        return protocols;
    }

    public void setProtocols(List protocols) {
        this.protocols = protocols;
    }
}
