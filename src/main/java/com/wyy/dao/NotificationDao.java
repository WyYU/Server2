package com.wyy.dao;

import com.wyy.po.Notification;

import java.util.List;

/**
 * Created by dell on 2019/3/24 0024.
 */
public interface NotificationDao {
    void createNoti(int tid,String context);
    void del();
    List<Notification> queryNoti(int tid);

}
