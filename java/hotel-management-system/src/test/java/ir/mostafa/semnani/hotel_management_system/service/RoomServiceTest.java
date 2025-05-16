package ir.mostafa.semnani.hotel_management_system.service;

import ir.mostafa.semnani.hotel_management_system.dto.request.SaveRoomRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@SpringBootTest
public class RoomServiceTest {

    @Autowired
    private RoomService roomService;

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
