package com.example.MyBookShopApp.data;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;

@Repository
public abstract class AbstractHibernateDao<T> {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    Class<T> clazz;

    public T getOne(Long id){
        return getSession().find(clazz, id);
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public Session getSession(){
        return entityManagerFactory.createEntityManager().unwrap(Session.class);
    }
}
