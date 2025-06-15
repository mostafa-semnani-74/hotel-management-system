package service

import (
	"fmt"
	"hms/repository"
	"net/http"

	"github.com/gin-gonic/gin"
)

type RoomService struct {
	roomRepository repository.RoomRepository
}

func NewRoomService(roomRepository repository.RoomRepository) *RoomService {
	return &RoomService{roomRepository: roomRepository}
}

func (s *RoomService) GetRooms(c *gin.Context) {
	rooms, err := s.roomRepository.FindAll()

	if err != nil {
		panic(err)
	}

	fmt.Println("get all rooms : ", rooms)

	c.IndentedJSON(http.StatusOK, rooms)
}
