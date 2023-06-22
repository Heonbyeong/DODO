package com.example.dodo.data.util

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalTime

class DateTimeConverter {

    @TypeConverter
    fun localDateToString(localDate: LocalDate?): String? =
        localDate?.toString()

    @TypeConverter
    fun stringToLocalDate(string: String?): LocalDate? =
        LocalDate.parse(string)

    @TypeConverter
    fun localTimeToString(localTime: LocalTime?): String? =
        localTime?.toString()

    @TypeConverter
    fun stringToLocalTime(string: String?): LocalTime? =
        LocalTime.parse(string)
}