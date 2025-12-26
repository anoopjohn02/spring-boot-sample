package com.anoop.examples.services;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LifeCycleService implements BeanPostProcessor, BeanFactoryAware, BeanNameAware {
    private static final Logger log = LoggerFactory.getLogger(LifeCycleService.class);

    public LifeCycleService() {
        log.info("1. UserService constructor");
    }

    @Override
    public void setBeanName(String name) {
        log.info("2. UserService BeanNameAware");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("3. UserService BeanFactoryAware");
    }

    @PostConstruct
    public void postConstruct() {
        log.info("4. UserService PostConstruct");
    }

    @Override
    public @Nullable Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("5. UserService postProcessBeforeInitialization");
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public @Nullable Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("6. UserService postProcessAfterInitialization");
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("7. UserService PreDestroy");
    }

}
