package com.eureka;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EurekaApplicationTests {

    @Test
    void mainMethodRunsWithoutExceptions() {
        assertDoesNotThrow(() -> EurekaApplication.main(new String[]{}));
    }

    @Test
    void eurekaApplicationHasSpringBootApplicationAnnotation() {
        assertTrue(
                EurekaApplication.class.isAnnotationPresent(SpringBootApplication.class),
                "EurekaApplication debe estar anotada con @SpringBootApplication"
        );
    }

    @Test
    void eurekaApplicationHasEnableEurekaServerAnnotation() {
        assertTrue(
                EurekaApplication.class.isAnnotationPresent(EnableEurekaServer.class),
                "EurekaApplication debe estar anotada con @EnableEurekaServer"
        );
    }

    @Test
    void springApplicationRunIsInvokable() {
        SpringApplication app = new SpringApplication(EurekaApplication.class);
        assertNotNull(app);
    }
}
