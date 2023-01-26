package com.justudy.backend.GroupCall.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
*
* ServerEndpointConfig : ServerEndpoint 설정 클래스
 * 
 * 웹소켓의 경우 DI가 기본적으로 안된다. 응답이 올때마다 초기화되어 생성되어 문제가 생긴다.
* 따라서 다음과 같은 설정으로 BeanFatory에 저장하여 응답이 올때마다 여기서 뽑아낸다.
*
*
**/
@Configuration
public class ServerEndpointConfig extends javax.websocket.server.ServerEndpointConfig.Configurator implements ApplicationContextAware {
    //BeanFactory : 빈의 생성과 관계설정 제어를 담당 오브젝트
    private static volatile BeanFactory context;

    // 연결 때마다 마지막에 해당 빈이 불러오도록 설정한다. (액티비티 내에서 접근 가능한 인스턴스)
    @Override
    public <T> T getEndpointInstance(Class<T> clazz) throws InstantiationException {
        return context.getBean(clazz);
    }

    //ApplicationContext : 해당 애플리케이션에 대한 구성정보를 제공하는 인터페이스
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ServerEndpointConfig.context = applicationContext;
    }
}