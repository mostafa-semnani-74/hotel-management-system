package main

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

type Room struct {
	ID           uint64 `json:"id"`
	NumberOfBeds uint32 `json:"numberOfBeds"`
}

var rooms = []Room{
	{ID: 1, NumberOfBeds: 3},
}

func getRooms(c *gin.Context) {
	c.IndentedJSON(http.StatusOK, rooms)
}

func main() {
	router := gin.Default()

	router.GET("/api/v1/rooms", getRooms)

	router.Run("localhost:8080")
}
