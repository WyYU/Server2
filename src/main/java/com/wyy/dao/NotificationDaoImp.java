package com.wyy.dao;

import com.wyy.po.Notification;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

/**
 * Created by dell on 2019/3/24 0024.
 */
public class NotificationDaoImp implements NotificationDao {
    private org.hibernate.cfg.Configuration cfg;
    private SessionFactory sessionFactory;
    public NotificationDaoImp() {
        cfg = new Configuration().configure("hibernate.cfg.xml");
        sessionFactory =cfg.buildSessionFactory();
    }
    @Override
    public void createNoti(int tid,String context) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            Notification notification = new Notification();
            notification.setTid(tid);
            notification.setContext(context);
            java.util.Date date = new java.util.Date();
            Date sqldate = new Date(date.getTime());
            notification.setData(sqldate);
            session.save(notification);
        }catch (Exception e){

        }
        transaction.commit();
        session.close();
    }

    @Override
    public void del() {

    }

    @Override
    public List<Notification> queryNoti(int tid) {
        List<Notification> list = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Query<Notification> query;
        query = session.createQuery("from Notification where tid = "+tid+"");
        list = query.list();
        return list;
    }

    @Test
    public void test(){
       // createNoti(24,"new");
        System.out.println(queryNoti(14));
    }
}
