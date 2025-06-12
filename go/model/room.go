package model

type Room struct {
	ID           uint64 `gorm:"type:int;primaryKey" json:"id"`
	NumberOfBeds uint32 `gorm:"type:int" json:"numberOfBeds"`
}
