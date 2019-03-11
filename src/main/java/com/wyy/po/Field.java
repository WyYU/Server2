package com.wyy.po;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by dell on 2019/3/9 0009.
 */
@Entity
public class Field {
    private String name;
    private double prive;
    private String address;
    private String rate;

    @Id
    @Column(name = "name", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "prive", nullable = false, precision = 0)
    public double getPrive() {
        return prive;
    }

    public void setPrive(double prive) {
        this.prive = prive;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 20)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "rate", nullable = true, length = 30)
    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Field field = (Field) o;

        if (Double.compare(field.prive, prive) != 0) return false;
        if (name != null ? !name.equals(field.name) : field.name != null) return false;
        if (address != null ? !address.equals(field.address) : field.address != null) return false;
        if (rate != null ? !rate.equals(field.rate) : field.rate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(prive);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        return result;
    }

}
