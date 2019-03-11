package com.wyy.po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dell on 2019/3/9 0009.
 */
@Entity
public class User implements Serializable{
    private int id;
    private String username;
    private String password;
    private Integer tid;
    private Integer num;
    private String imagepatch;
    private Integer level;
    private int goal;
    private Integer assisting;
    private String position;
    private Double balance;
    private com.wyy.po.Team Team;
    private Team team;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "tid", nullable = true)
    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    @Basic
    @Column(name = "num", nullable = true)
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Basic
    @Column(name = "imagepatch", nullable = true, length = 50)
    public String getImagepatch() {
        return imagepatch;
    }

    public void setImagepatch(String imagepatch) {
        this.imagepatch = imagepatch;
    }

    @Basic
    @Column(name = "level", nullable = true)
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Basic
    @Column(name = "goal", nullable = false)
    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    @Basic
    @Column(name = "assisting", nullable = true)
    public Integer getAssisting() {
        return assisting;
    }

    public void setAssisting(Integer assisting) {
        this.assisting = assisting;
    }

    @Basic
    @Column(name = "position", nullable = true, length = 10)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Basic
    @Column(name = "balance", nullable = true, precision = 0)
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (goal != user.goal) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (tid != null ? !tid.equals(user.tid) : user.tid != null) return false;
        if (num != null ? !num.equals(user.num) : user.num != null) return false;
        if (imagepatch != null ? !imagepatch.equals(user.imagepatch) : user.imagepatch != null) return false;
        if (level != null ? !level.equals(user.level) : user.level != null) return false;
        if (assisting != null ? !assisting.equals(user.assisting) : user.assisting != null) return false;
        if (position != null ? !position.equals(user.position) : user.position != null) return false;
        if (balance != null ? !balance.equals(user.balance) : user.balance != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (tid != null ? tid.hashCode() : 0);
        result = 31 * result + (num != null ? num.hashCode() : 0);
        result = 31 * result + (imagepatch != null ? imagepatch.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + goal;
        result = 31 * result + (assisting != null ? assisting.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "TID", referencedColumnName = "TID",insertable = false,updatable = false)
    public com.wyy.po.Team getTeam() {
        return Team;
    }

    public void setTeam(com.wyy.po.Team team) {
        Team = team;
    }
}
