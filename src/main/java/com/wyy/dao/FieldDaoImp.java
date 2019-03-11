package com.wyy.dao;

import com.wyy.po.Field;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * Created by dell on 2019/3/9 0009.
 */
public class FieldDaoImp implements FieldDao {
    private org.hibernate.cfg.Configuration cfg;
    private SessionFactory sessionFactory;

    public FieldDaoImp (){
        cfg = new Configuration().configure("hibernate.cfg.xml");
        sessionFactory =cfg.buildSessionFactory();
    }
    @Override
    public Field queryField(String name) {
        return null;
    }

    @Override
    public void addnew(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction= session.getTransaction();
        transaction.begin();
        Field field = new Field();
        field.setName(name);
        field.setAddress("黑龙江省哈尔滨市香坊区香电街20-3");
        field.setPrive(400);
        session.save(field);
        transaction.commit();
        session.close();
    }

    @Override
    public void del(String name) {

    }

    @Override
    public void update(String name) {

    }

    @Test
    public void test(){
        addnew("天行健室内");
    }
}
