package com.ruthvikbr.hospital_demo.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "History")
data class Medical_History (
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val name:String,
    val surgeries:String,
    val CurrentMedicalCondition:String
)