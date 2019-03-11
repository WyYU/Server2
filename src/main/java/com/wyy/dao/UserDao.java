package com.wyy.dao;

import com.wyy.po.User;

/**
 * Created by dell on 2019/3/9 0009.
 */
public interface UserDao {
    public void add(String name ,String pwd);
    public User queryId(int id);
    public User queryName(String name);
    public void del(int id);
    public void updata(int id);
    public void joinTeam(int uid,int tid);
    public void exitTeam(int uid);

}
