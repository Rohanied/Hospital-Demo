package com.ruthvikbr.hospital_demo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Appointment")
data class Appointment (
   @PrimaryKey(autoGenerate = true)
   val id:Long,
   val Doctor_name:String,
   val date:String,
   val symptoms:String,
   val Diagnosis:String,
   val prescription:String
)