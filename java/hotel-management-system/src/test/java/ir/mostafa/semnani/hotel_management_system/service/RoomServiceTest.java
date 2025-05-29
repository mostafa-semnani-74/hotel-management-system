package ir.mostafa.semnani.hotel_management_system.service;

import ir.mostafa.semnani.hotel_management_system.dto.request.SaveRoomRequestDTO;
import ir.mostafa.semnani.hotel_management_system.entity.Room;
import ir.mostafa.semnani.hotel_management_system.repository.RoomRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import reactor.test.StepVerifier;

@SpringBootTest
@ActiveProfiles("test")
public class RoomServiceTest {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomService roomService;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16-alpine"
    );

    @BeforeEach
    public void init() {
        roomRepository.deleteAll().subscribe();
    }

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @AfterEach
    public void afterEach() {
        roomRepository.deleteAll().subscribe();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        var r2dcUrl = postgres.getJdbcUrl().replace("jdbc", "r2dbc");
        registry.add("spring.r2dbc.url", () -> r2dcUrl);
        registry.add("spring.r2dbc.username", postgres::getUsername);
        registry.add("spring.r2dbc.password", postgres::getPassword);
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

    @Test
    public void get_all_success_test() {
        var room = roomRepository.save(new Room(null, 2)).block();

        StepVerifier
                .create(roomService.getAll())
                .consumeNextWith(getAllRoomsResponseDTO -> {
                         Assertions.assertNotNull(getAllRoomsResponseDTO);
                         Assertions.assertEquals(room.getId(), getAllRoomsResponseDTO.id());
                         Assertions.assertEquals(room.getNumberOfBeds(), getAllRoomsResponseDTO.numberOfBeds());
                        })
                .verifyComplete();
    }

}
