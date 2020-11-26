package model;

import java.io.Serializable;
import java.util.Objects;

public class CommodityAvr4 implements Comparable<CommodityAvr4>, Serializable {

    private long quantity;
    private float price;
    public CommodityAvr4(){

    }

    public CommodityAvr4(float price, long quantity) {
        this.quantity = quantity;
        this.price = price;
    }



    public long getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return " " + quantity +
                " " + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommodityAvr4 that = (CommodityAvr4) o;
        return quantity == that.quantity &&
                Float.compare(that.price, price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, price);
    }

    @Override
    public int compareTo(CommodityAvr4 o) {
        if(this.hashCode()<o.hashCode()){
            return -1;
        }
        else if(this.hashCode()>o.hashCode()){
            return +1;
        }
        return 0;
    }
}
