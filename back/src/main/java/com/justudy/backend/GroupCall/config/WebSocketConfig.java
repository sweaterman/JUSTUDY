package com.justudy.backend.GroupCall.config;

import com.justudy.backend.GroupCall.model.RoomManager;
import com.justudy.backend.GroupCall.model.UserRegistry;
import org.kurento.client.KurentoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
*
* WebSocketConfig : 웹소켓 설정 클래스
*
* 해당설정을 통하여 Bean어노테이션을 한 클래스는 DI가 가능하다 (외부 라이브러리를 쓰기 때문에 필요)
 * ServerEndpointExportert를 설정해주어야만 웹소켓이 가능하다
*
**/
@Configuration
public class WebSocketConfig {

    @Bean
    public UserRegistry registry() {
        return new UserRegistry();
    }
//    "wss://kurento:8888/kurento"
    @Bean
    public KurentoClient kurentoClient() {
        return KurentoClient.create("wss://kurento:8888/kurento");
    }

    @Bean
    public RoomManager roomManager() {
        return new RoomManager();
    }
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        /*
            2022.10.26[프뚜]:
                Spring에서 Bean은 싱글톤으로 관리되지만,
                @ServerEndpoint 클래스는 WebSocket이 생성될 때마다 인스턴스가 생성되고
                JWA에 의해 관리되기 때문에 Spring의 @Autowired가 설정된 멤버들이 초기화 되지 않습니다.
                연결해주고 초기화해주는 클래스가 필요합니다.
         */
        return new ServerEndpointExporter();
    }

}
