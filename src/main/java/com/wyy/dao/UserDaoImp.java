package com.wyy.dao;

import com.wyy.po.Team;
import com.wyy.po.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dell on 2019/3/9 0009.
 */
public class UserDaoImp implements UserDao {
    private static UserDaoImp userDaoImp;
    NotificationDaoImp notificationDaoImp;
    public static synchronized UserDaoImp getInstance(){
        if(userDaoImp==null){
            userDaoImp = new UserDaoImp();
        }
        return userDaoImp;
    }
    private org.hibernate.cfg.Configuration cfg;
    private SessionFactory sessionFactory;
    public  UserDaoImp(){
        notificationDaoImp = NotificationDaoImp.getNotificationDaoImp();
        cfg = new Configuration().configure("hibernate.cfg.xml");
        sessionFactory =cfg.buildSessionFactory();
    }
    @Override
    public void add(String name, String pwd) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            User user = new User();
            user.setUsername(name);
            user.setPassword(pwd);
            user.setImagepatch("headpic/apple.png");
            user.setLevel(1);
            user.setTid(26);
            user.setAssisting(0);
            user.setGoal(0);
            user.setPosition("null");
            user.setBalance(0.00);
            user.setNum(0);
            session.save(user);
        }
        catch (Exception e){
            transaction.rollback();
        }
        transaction.commit();
        session.close();
    }

    @Override
    public User queryId(int id) {
        Session session = sessionFactory.openSession();
        Query query =session.createQuery("from User where id = "+id);
        List<User> list =query.list();
        if (list.size()>0) {
            User u = list.get(0);
            return u;
        }
        session.close();
        return null;
    }

    @Override
    public User queryName(String name) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM User where username = '"+name+"'");
        List<User> list =query.list();
        if (list.size()>0) {
            User u = list.get(0);
            return u;
        }
        session.close();
        return null;
    }

    @Override
    public void del(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        User user = session.load(User.class,id);
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void updata(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        User user = session.load(User.class,id);
        user.setGoal(1);
        user.setAssisting(1);
        session.save(user);
        transaction.commit();
        session.close();

    }

    public void updatahead(int uid ,String headpath){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        User user = session.load(User.class,uid);
        user.setImagepatch(headpath);
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void joinTeam(int uid, int tid) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        User user = session.load(User.class,uid);
        Team team = session.load(Team.class,tid);
        System.out.println(user.getUsername());
        System.out.println(team.getTname());
        System.out.println("队员移动");
        notificationDaoImp =  new NotificationDaoImp();
        notificationDaoImp.createNoti(user.getTid(),user.getUsername()+"退出"+user.getTeam().getTname());
        notificationDaoImp.createNoti(team.getTid(),user.getUsername()+"加入"+team.getTname());
        user.setTeam(team);
        user.setTid(tid);
        team.getPlayers().add(user);
        session.save(user);
        session.save(team);
        transaction.commit();
        session.close();
    }

    @Override
    public void exitTeam(int uid) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            User user = session.load(User.class, uid);
            int tid = user.getTid();
            Team team = session.load(Team.class, tid);
            System.out.println(user.getUsername());
            System.out.println(team.getTname());
            team.getPlayers().remove(user);
            user.setTeam(team);
            user.setTid(13);
        } catch (Exception e){
            transaction.rollback();
        }
        transaction.commit();
        session.close();
    }

    public int regiest(String username,String password){
        if (queryName(username)!=null){
            //数据库中有同用户名时 注册失败 返回0;
            return 0;
        }else {
            add(username,password);
            return  1;
        }
    }

    public int login(String username,String password){
        if ((queryName(username)!=null)&&(queryName(username).getPassword().equals(password))){
            return 1;
        }else return 0;
    }

    public int deluser(int uid ){
        if (queryId(uid)!=null){
            del(uid);
            return 1;
        }
        else return 0;
    }

    public int updatameg(int uid,int playerid,String pos){
        Session session = sessionFactory.openSession();
        Transaction transaction= session.getTransaction();
        transaction.begin();
        try {
            User user = session.load(User.class,uid);
            user.setPosition(pos);
            user.setNum(playerid);
            session.update(user);
        }catch (Exception e){
            transaction.rollback();
            return 0;
        }
        transaction.commit();
        session.close();
        return 1;
    }

    public int levelChange(int uid ,int level){
        Session session =sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        User user = session.load(User.class,uid);
        user.setLevel(level);
        session.update(user);
        transaction.commit();
        session.close();
        return 1;
    }

    public List<User> searchUser(String uname){
        Session session =sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Iterator<User> i =session.createQuery("FROM User where username like '%"+uname+"%'").iterate();
        List<User> users = new ArrayList<>();
        while (i.hasNext()){
            User u = i.next();
            users.add(u);
            System.out.println(u.getUsername());
        }
        transaction.commit();
        session.close();
        return users;
    }
    public int Changemsg(String uid ,String ass ,String goal ,String lv){
        Session session =sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            User user = session.load(User.class,Integer.parseInt(uid));
            user.setAssisting(Integer.valueOf(ass));
            user.setGoal(Integer.parseInt(goal));
            user.setLevel(Integer.parseInt(lv));
            session.update(user);
            transaction.commit();
            session.close();
        }catch (Exception e) {
            return 0;
        }
        return 1;

    }



    @Test
    public void Test(){
        System.out.println(Changemsg("16","2","2","2"));
    }
}
