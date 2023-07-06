package com.banana.event.starter;

import com.banana.event.starter.base.Event;
import com.banana.event.starter.base.EventConsumerTask;
import com.banana.event.starter.extension.ConsumerTaskRepository;
import com.banana.event.starter.extension.EventIdFactory;
import com.banana.event.starter.extension.EventListener;
import com.banana.event.starter.extension.EventRepository;
import com.banana.event.starter.extension.impl.DefaultEventIdFactory;
import com.banana.event.starter.extension.impl.DefaultEventListener;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @事件启动器
 * @author: banana
 * @date 2023-07-05 18:09
 * @author yangxiyu
 */
@Configuration
public class EventStarter implements ApplicationListener<ContextRefreshedEvent> {


    /**
     *
     */
    private static ApplicationContext applicationContext;

    /**
     * 校验组件能否正常启动，关键的扩展点是否有实现
     * @param event
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        ApplicationContext context =  event.getApplicationContext();
        // 事件仓储EventRepository
        EventRepository repository =  context.getBean(EventRepository.class);
        ConsumerTaskRepository consumerTaskRepository =  context.getBean(ConsumerTaskRepository.class);
        applicationContext = context;
    }


    /**
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

    /**
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> clazz){
        return applicationContext.getBeansOfType(clazz);
    }

    /**
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name) {

        if (applicationContext==null) {
        }
        return (T) applicationContext.getBean(name);

    }

    @Bean
    @ConditionalOnMissingBean(EventIdFactory.class)
    public EventIdFactory defaultIdFactory(){
        return new DefaultEventIdFactory();
    }

    @Bean
    @ConditionalOnMissingBean(EventListener.class)
    public EventListener defaultEventListener(){
        return new DefaultEventListener();
    }

    @Bean
    public EventCoordinator eventCoordinator(){
        return new EventCoordinator();
    }

    @Bean
    public EventRegister eventRegister(){
        return new EventRegister();
    }
}
