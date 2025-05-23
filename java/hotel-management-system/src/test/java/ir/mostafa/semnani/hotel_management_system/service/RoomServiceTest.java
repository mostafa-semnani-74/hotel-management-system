package ir.mostafa.semnani.hotel_management_system.service;

import ir.mostafa.semnani.hotel_management_system.dto.request.SaveRoomRequestDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.test.StepVerifier;

@SpringBootTest
//@Testcontainers
@ActiveProfiles("test")
public class RoomServiceTest {

    @Autowired
    private RoomService roomService;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16-alpine"
    );

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Test
    public void save_success_test() {
        int numberOfBeds = 3;
        var saveRoomRequestDTO = new SaveRoomRequestDTO(
                numberOfBeds
        );

        StepVerifier
                .create(roomService.save(saveRoomRequestDTO))
                .consumeNextWith(saveRoomResponseDTO -> {
                    Assertions.assertNotNull(saveRoomResponseDTO);
                    Assertions.assertEquals(numberOfBeds, saveRoomRequestDTO.numberOfBeds());
                    Assertions.assertNotNull(saveRoomResponseDTO.id());
                    Assertions.assertTrue(saveRoomResponseDTO.id() > 0);
                })
                .verifyComplete();
    }

}
