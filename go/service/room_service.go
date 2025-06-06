package service

import (
	"hms/model"
	"net/http"

	"github.com/gin-gonic/gin"
)

var rooms = []model.Room{
	{ID: 1, NumberOfBeds: 3},
}

func GetRooms(c *gin.Context) {
	c.IndentedJSON(http.StatusOK, rooms)
}
