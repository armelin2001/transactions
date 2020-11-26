package model;
import java.io.Serializable;
import java.util.Objects;
public class Commodity6 implements Comparable<Commodity6>,Serializable {
    private String nameComm;
    private String unit;
    private int ano;

    public Commodity6(String nameComm, String unit, int ano) {
        this.nameComm = nameComm;
        this.unit = unit;
        this.ano = ano;
    }

    @Override
    public String toString() {
        return  nameComm +
                "|" + unit +
                "|" + ano +"  ";
    }

    public String getNameComm() {
        return nameComm;
    }

    public void setNameComm(String nameComm) {
        this.nameComm = nameComm;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commodity6 that = (Commodity6) o;
        return ano == that.ano &&
                Objects.equals(nameComm, that.nameComm) &&
                Objects.equals(unit, that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameComm, unit, ano);
    }

    @Override
    public int compareTo(Commodity6 o) {
        if(this.hashCode()<o.hashCode()){
            return -1;
        }
        else if(this.hashCode()>o.hashCode()){
            return +1;
        }
        return 0;
    }
}
