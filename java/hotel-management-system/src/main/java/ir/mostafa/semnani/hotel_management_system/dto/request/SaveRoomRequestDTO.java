package ir.mostafa.semnani.hotel_management_system.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SaveRoomRequestDTO(
        @NotNull(message = "numberOfBeds should not be null")
        @Min(value = 1, message = "numberOfBeds should be greater than 0")
        @Max(value = 4, message = "numberOfBeds should be less than 5")
        int numberOfBeds
) {
}
