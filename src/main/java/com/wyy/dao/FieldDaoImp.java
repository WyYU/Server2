package com.wyy.dao;

import com.wyy.po.Field;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.List;

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
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        List<Field> list = session.createQuery("FROM Field where name = '"+name+"'").list();
        if (list.size()>0){
            return list.get(0);
        }
        return  null;
    }

    @Override
    public int addnew(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction= session.getTransaction();
        transaction.begin();
        try {
            Field field = new Field();
            field.setName(name);
            field.setAddress("地址");
            field.setPrive(400);
            session.save(field);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        finally {
            session.close();
        }
        return 1;
    }

    @Override
    public void del(String name) {

    }

    @Override
    public void update(String name) {

    }

    @Test
    public void test(){
        System.out.println(queryField("camp no5"));
    }
}
