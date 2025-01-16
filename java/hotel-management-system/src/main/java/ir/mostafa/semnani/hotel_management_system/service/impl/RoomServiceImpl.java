package ir.mostafa.semnani.hotel_management_system.service.impl;

import ir.mostafa.semnani.hotel_management_system.entity.Room;
import ir.mostafa.semnani.hotel_management_system.repository.RoomRepository;
import ir.mostafa.semnani.hotel_management_system.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static ir.mostafa.semnani.hotel_management_system.config.VirtualThreadPool.getVirtualThreadPool;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public List<Room> findAll() {
        try {
            return CompletableFuture
                    .supplyAsync(roomRepository::findAll, getVirtualThreadPool())
                    .get();
        } catch (Exception e) {
            log.error("error in room service in find all : ", e);
            throw new RuntimeException("internal server error");
        }
    }

}
