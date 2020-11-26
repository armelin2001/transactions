package model;

import java.io.Serializable;
import java.util.Objects;
//
public class CommodityAvr implements Comparable<CommodityAvr>,Serializable {
    private float price;
    private String unit;
    private String category;
    private long quantity;
    private int ano;
    private float avr;
    public CommodityAvr(String unit, int ano, String category) {
        this.unit = unit;
        this.ano = ano;
        this.category = category;
    }

    public CommodityAvr(float price, long quantity) {
        this.price = price;
        this.quantity = quantity;
    }
    public CommodityAvr(){
    }

    public CommodityAvr(float price) {
        this.avr = price;
    }

    public float getAvr() {
        return avr;
    }

    public void setAvr(float avr) {
        this.avr = avr;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return ""+getUnit()+""+getAno()+""+getCategory();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommodityAvr that = (CommodityAvr) o;
        return Float.compare(that.price, price) == 0 &&
                quantity == that.quantity &&
                ano == that.ano &&
                Objects.equals(unit, that.unit) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, unit, category, quantity, ano);
    }


    @Override
    public int compareTo(CommodityAvr o) {
        if(this.hashCode()<o.hashCode()){
            return -1;
        }
        else if(this.hashCode()>o.hashCode()){
            return +1;
        }
        return 0;
    }
}
