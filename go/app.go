package main

import (
	"hms/service"

	"github.com/gin-gonic/gin"
)

func main() {
	router := gin.Default()

	router.GET("/api/v1/rooms", service.GetRooms)

	router.Run("localhost:8080")
}
