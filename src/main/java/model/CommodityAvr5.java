package model;

import org.apache.spark.api.java.JavaPairRDD;

import java.io.Serializable;
import java.util.Objects;

public class CommodityAvr5  implements Serializable,Comparable<CommodityAvr5> {
    private String quantity_name;
    private String category;
    private int ano;
    private long quantity;
    private float price;
    private int qtd;

    public CommodityAvr5(String unit, int ano, String category) {
        this.quantity_name = unit;
        this.ano = ano;
        this.category = category;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
    public CommodityAvr5() {

    }
    public CommodityAvr5(float price, int qtd){
        this.price=price;
        this.qtd=qtd;
    }
    public CommodityAvr5(long quantity, float price, int qtd) {
        this.quantity = quantity;
        this.price=price;
        this.qtd=qtd;
    }

    public String getQuantity_name() {
        return quantity_name;
    }

    public void setQuantity_name(String quantity_name) {
        this.quantity_name = quantity_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }


    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return ""+getQuantity_name()+"|"+ getAno() + "|" + getCategory();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommodityAvr5 that = (CommodityAvr5) o;
        return ano == that.ano &&
                quantity == that.quantity &&
                Float.compare(that.price, price) == 0 &&
                qtd == that.qtd &&
                Objects.equals(quantity_name, that.quantity_name) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity_name, category, ano, quantity, price, qtd);
    }

    @Override
    public int compareTo(CommodityAvr5 o) {
        if(this.hashCode()<o.hashCode()){
            return -1;
        }
        else if(this.hashCode()>o.hashCode()){
            return +1;
        }
        return 0;
    }
}
//country_or_area0 ;year1 ;comm_code2;commodity3;flow4;trade_usd5;weight_kg6;quantity_name7;quantity8;category
/*
private String quantity_name;
    private String category;
    private int ano;
    private long quantity;
    private float price;


 */