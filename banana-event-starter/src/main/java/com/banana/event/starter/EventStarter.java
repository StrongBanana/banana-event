package com.banana.event.starter;

import com.banana.event.starter.extension.ConsumerTaskRepository;
import com.banana.event.starter.extension.EventIdFactory;
import com.banana.event.starter.extension.EventWarning;
import com.banana.event.starter.extension.EventRepository;
import com.banana.event.starter.extension.impl.DefaultEventIdFactory;
import com.banana.event.starter.extension.impl.DefaultEventWarning;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Map;

/**
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
    @ConditionalOnMissingBean(EventWarning.class)
    public EventWarning defaultEventListener(){
        return new DefaultEventWarning();
    }

    @Bean
    public EventCoordinator eventCoordinator(){
        return new EventCoordinator();
    }

    @Bean
    public EventConsumerRegister consumerRegister(){
        return new EventConsumerRegister();
    }
}
