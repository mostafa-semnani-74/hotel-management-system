package service

import (
	"fmt"
	"hms/model"
	"net/http"

	"github.com/gin-gonic/gin"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

func GetRooms(c *gin.Context) {
	dsn := "host=localhost user=postgres password=1234 dbname=postgres port=5432 sslmode=disable"
	db, err := gorm.Open(postgres.Open(dsn), &gorm.Config{})
	if err != nil {
		panic("failed to connect database")
	}

	var rooms []model.Room
	result := db.Find(&rooms) // finds all users

	if result.Error != nil {
		panic(result.Error)
	}

	fmt.Println("get all rooms : ", rooms)

	c.IndentedJSON(http.StatusOK, rooms)
}
