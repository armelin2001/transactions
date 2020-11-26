package model;

import javax.print.DocFlavor;
import java.io.Serializable;
import java.util.Objects;

public class Commodity7 implements Serializable,Comparable<Commodity7> {
    private String year;
    private String flow;

    public Commodity7(String flow, String ano) {
        this.flow = flow;
        this.year = ano;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


    public Commodity7() {
    }
    @Override
    public String toString() {
        return ""+getFlow()+"|"+ getYear()+"|";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commodity7 that = (Commodity7) o;
        return Objects.equals(year, that.year) &&
                Objects.equals(flow, that.flow);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, flow);
    }

    @Override
    public int compareTo(Commodity7 o) {
        if(this.hashCode()<o.hashCode()){
            return -1;
        }
        else if(this.hashCode()>o.hashCode()){
            return +1;
        }
        return 0;
    }
}
