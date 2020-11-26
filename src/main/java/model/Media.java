package model;

import java.io.Serializable;

public class Media implements Serializable {
    private long qtd;
    private float price;
    private long quantity;
    private float media;
    public Media() {

    }
    public Media(float media) {
        this.media=media;
    }
    public Media( float price,long qtd) {
        this.qtd = qtd;
        this.price = price;
    }
    public Media(long qtd, float price, long quantity) {
        this.qtd = qtd;
        this.price = price;
        this.quantity = quantity;
    }

    public long getQtd() {
        return qtd;
    }

    public void setQtd(long qtd) {
        this.qtd = qtd;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return " "+ media;
    }
}
