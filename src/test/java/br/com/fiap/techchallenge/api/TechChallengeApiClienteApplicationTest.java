package br.com.fiap.techchallenge.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class TechChallengeApiClienteApplicationTest {

    @BeforeEach
    void setUp() {
        TimeZone.setDefault(null);
    }

    @Test
    void deveExecutarComSucesso() {
        String[] args = new String[]{"--spring.profiles.active=test"};
        ConfigurableApplicationContext mockContext = mock(ConfigurableApplicationContext.class);

        try (MockedStatic<SpringApplication> mockedSpring = Mockito.mockStatic(SpringApplication.class)) {
            mockedSpring.when(() -> SpringApplication.run(eq(TechChallengeApiClienteApplication.class), any(String[].class)))
                    .thenReturn(mockContext);

            TechChallengeApiClienteApplication.main(args);

            assertEquals("UTC", TimeZone.getDefault().getID(), "TimeZone should be set to UTC");
            mockedSpring.verify(() -> SpringApplication.run(eq(TechChallengeApiClienteApplication.class), eq(args)));
        }
    }
}
