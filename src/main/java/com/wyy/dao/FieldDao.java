package com.wyy.dao;

import com.wyy.po.Field;

/**
 * Created by dell on 2019/3/9 0009.
 */
public interface FieldDao {
    Field queryField(String name);
    int addnew(String name);
    void del(String name);
    void update(String name);
}
