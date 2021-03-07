package com.example.MyBookShopApp.config;

import com.example.MyBookShopApp.data.TestEntity;
import com.example.MyBookShopApp.data.TestEntityDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateJdbcException;

import javax.persistence.EntityManagerFactory;
import java.util.logging.Logger;

@Configuration
public class CommandLineRunnerImpl implements CommandLineRunner {

    EntityManagerFactory entityManagerFactory;
    TestEntityDao testEntityDao;

    @Autowired
    public CommandLineRunnerImpl(EntityManagerFactory entityManagerFactory, TestEntityDao testEntityDao) {
        this.entityManagerFactory = entityManagerFactory;
        this.testEntityDao = testEntityDao;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i =0; i<5; i++){
            createTestEntity(new TestEntity());
        }
        
        TestEntity readTestEntity = testEntityDao.getOne(3L); //readTestEntityById(3L);
        if (readTestEntity != null){
            Logger.getLogger(CommandLineRunnerImpl.class.getSimpleName()).info("read " + readTestEntity.toString());
        }else {
            throw new NullPointerException();
        }
         TestEntity updatedTestEntityById = updateTestEntityById(5L);
        if (updatedTestEntityById != null){
            Logger.getLogger(CommandLineRunnerImpl.class.getSimpleName()).info("update " + updatedTestEntityById.toString());
        }else {
            throw new NullPointerException();
        }

        deleteTestEntityById(4L);

    }

    private void deleteTestEntityById(Long id) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        Transaction tx = null;

        try {
            tx= session.beginTransaction();
            TestEntity findEntity = readTestEntityById(id);
            TestEntity mergeTestEntity = (TestEntity) session.merge(findEntity);
            session.remove(mergeTestEntity);
            tx.commit();
        }catch (HibernateJdbcException hex){
            if (tx != null){
                tx.rollback();
            }else {
                hex.printStackTrace();
            }
        }finally {
            session.close();
        }
    }

    private TestEntity updateTestEntityById(Long id) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        Transaction tx = null;
        TestEntity result = null;

        try {
            tx= session.beginTransaction();
            TestEntity findEntity = readTestEntityById(id);
            findEntity.setData("NEW DATA UPDATE");
            result = (TestEntity) session.merge(findEntity);
            tx.commit();
        }catch (HibernateJdbcException hex){
            if (tx != null){
                tx.rollback();
            }else {
                hex.printStackTrace();
            }
        }finally {
            session.close();
        }
        return result;
    }

    private TestEntity readTestEntityById(Long id) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        Transaction tx = null;
        TestEntity result = null;

        try {
            tx= session.beginTransaction();
            result = session.find(TestEntity.class, id);
            tx.commit();
        }catch (HibernateJdbcException hex){
            if (tx != null){
                tx.rollback();
            }else {
                hex.printStackTrace();
            }
        }finally {
            session.close();
        }
        return result;
    }

    private void createTestEntity(TestEntity entity) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        Transaction tx = null;

        try {
            tx= session.beginTransaction();
            entity.setData(TestEntity.class.getSimpleName() + entity.hashCode());
            session.save(entity);

            tx.commit();
        }catch (HibernateJdbcException hex){
            if (tx != null){
                tx.rollback();
            }else {
                hex.printStackTrace();
            }
        }finally {
            session.close();
        }
    }
}
