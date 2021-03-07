package com.example.MyBookShopApp.config;

import com.example.MyBookShopApp.data.BookRepository;
import com.example.MyBookShopApp.data.TestEntity;
import com.example.MyBookShopApp.data.TestEntityCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class CommandLineRunnerImpl implements CommandLineRunner {

    private TestEntityCrudRepository testEntityCrudRepository;
    private BookRepository bookRepository;

    @Autowired
    public CommandLineRunnerImpl(TestEntityCrudRepository testEntityCrudRepository, BookRepository bookRepository) {
        this.testEntityCrudRepository = testEntityCrudRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 5; i++) {
            createTestEntity(new TestEntity());
        }

        TestEntity readTestEntity = readTestEntityById(3L);
        if (readTestEntity != null) {
            Logger.getLogger(CommandLineRunnerImpl.class.getSimpleName()).info("read " + readTestEntity.toString());
        } else {
            throw new NullPointerException();
        }
        TestEntity updatedTestEntityById = updateTestEntityById(5L);
        if (updatedTestEntityById != null) {
            Logger.getLogger(CommandLineRunnerImpl.class.getSimpleName()).info("update " + updatedTestEntityById.toString());
        } else {
            throw new NullPointerException();
        }

        deleteTestEntityById(4L);
        Logger.getLogger(CommandLineRunnerImpl.class.getSimpleName()).info( bookRepository.findBooksByAuthor_FirstName("Alejandrina").toString());
        Logger.getLogger(CommandLineRunnerImpl.class.getSimpleName()).info( bookRepository.customFindAllBooks().toString());
    }

    private void deleteTestEntityById(Long id) {
        TestEntity testEntity = readTestEntityById(id);
        testEntityCrudRepository.delete(testEntity);
    }

    private TestEntity updateTestEntityById(Long id) {
        TestEntity testEntity = readTestEntityById(id);
        testEntity.setData("NEW DATA UPDATE");
        testEntityCrudRepository.save(testEntity);
        return testEntity;
    }

    private TestEntity readTestEntityById(Long id) {
        return testEntityCrudRepository.findById(id).get();
    }

    private void createTestEntity(TestEntity entity) {
        entity.setData(entity.getClass().getSimpleName() + entity.hashCode());
        testEntityCrudRepository.save(entity);
    }
}
