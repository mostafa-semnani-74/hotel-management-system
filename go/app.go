package main

import (
	"hms/config"
	"hms/repository"
	"hms/service"

	"github.com/gin-gonic/gin"
)

func main() {
	router := gin.Default()

	db := config.SetupDatabase()

	roomRepository := repository.NewGormRoomRepository(db)

	roomService := service.NewRoomService(roomRepository)

	router.GET("/api/v1/rooms", roomService.GetRooms)

	router.Run("localhost:8080")
}
