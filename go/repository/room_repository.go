package repository

import (
	"errors"
	"hms/model"

	"gorm.io/gorm"
)

// TODO
type RoomRepository interface {
	FindAll() (*[]model.Room, error)
}

type GormRoomRepository struct {
	db *gorm.DB
}

func NewGormRoomRepository(db *gorm.DB) RoomRepository {
	return &GormRoomRepository{db}
}

func (r *GormRoomRepository) FindAll() (*[]model.Room, error) {
	var rooms []model.Room
	err := r.db.Find(&rooms).Error

	if errors.Is(err, gorm.ErrRecordNotFound) {
		return nil, nil
	}

	return &rooms, err
}
