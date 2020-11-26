package model;

import java.io.Serializable;
import java.util.Objects;

public class CommodityModel implements Comparable<CommodityModel>,Serializable {
    private String commodityName;
    private String flow;
    private int qtd;
    public CommodityModel(CommodityModel cmTemp) {
    }
    public CommodityModel(String commodityName,int qtd){
        this.qtd = qtd;
        this.commodityName = commodityName;
    }
    public CommodityModel(String commodityName, String flow,int qtd) {
        this.commodityName = commodityName;
        this.flow = flow;
        this.qtd = qtd;
    }

    public CommodityModel(String commodityName, String flow) {
        this.commodityName = commodityName;
        this.flow = flow;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    @Override
    public String toString() {
        return " "+getFlow()+
                "     "+getCommodityName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommodityModel that = (CommodityModel) o;
        return qtd == that.qtd &&
                Objects.equals(commodityName, that.commodityName) &&
                Objects.equals(flow, that.flow);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commodityName, flow, qtd);
    }


    @Override
    public int compareTo(CommodityModel o) {
        if(this.hashCode()<o.hashCode()){
            return -1;
        }
        else if(this.hashCode()>o.hashCode()){
            return +1;
        }
        return 0;
    }
}
