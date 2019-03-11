package com.wyy.dao;

import com.wyy.po.Team;
import com.wyy.po.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.sql.Date;
import java.util.Iterator;

/**
 * Created by dell on 2019/3/9 0009.
 */
public class TeamDaoImp {
    private org.hibernate.cfg.Configuration cfg;
    private SessionFactory sessionFactory;

    public TeamDaoImp (){
        cfg = new Configuration().configure("hibernate.cfg.xml");
        sessionFactory =cfg.buildSessionFactory();
    }
    public void add (String teamname){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Team team = new Team();
        team.setTname(teamname);
        java.util.Date date = new java.util.Date();
        Date sqldate = new Date(date.getTime());

        team.setCreateTime(sqldate);
        session.save(team);
        transaction.commit();
        session.close();
    }

    public void jointeam(int uid ,int tid){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        User u = session.load(User.class,uid);
        Team team = session.load(Team.class,tid);

        u.setTeam(team);
        team.getPlayers().add(u);
        u.setTid(tid);
        session.save(u);
        session.save(team);
        transaction.commit();
        session.close();
    }
    public void delteam(int tid) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Team team = session.load(Team.class,tid);
        session.delete(team);
        transaction.commit();
        session.close();
    }

    public void updataTeam(int tid,String introduce){
        Session session =sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Team team = session.load(Team.class,tid);
        team.setIntroduce(introduce);
        session.update(team);
        transaction.commit();
        session.close();
    }
    @Test
    public void test(){

    }
    @Test
    public Iterator<User> qeryTeam(int tid){
        Session s =sessionFactory.openSession();
        Team team = s.load(Team.class,14);
        Iterator<User> userIterator = team.getPlayers().iterator();
        return userIterator;
    }

}
