package com.pagetyler.nasadaillypict.adapter

import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime


fun now(): Instant {
    return Instant.now()
}

fun hereAndNow(): ZonedDateTime {
    return ZonedDateTime.ofInstant(now(), ZoneId.systemDefault())
}

fun getToday(): ZonedDateTime {
    return hereAndNow()
}

fun getYesterday(): ZonedDateTime {
    return hereAndNow().minusDays(1)
}

fun getPreviousDay(dateIn: ZonedDateTime): ZonedDateTime {
    return dateIn.minusDays(1)
}

fun getNextDay(dateIn: ZonedDateTime): ZonedDateTime {
    return dateIn.plusDays(1)
}
fun getDateAdd (dateIn: ZonedDateTime, days : Long) : ZonedDateTime {
    return dateIn.plusDays(days)
}
fun getDateSubtract(dateIn: ZonedDateTime, days : Long): ZonedDateTime {
    return dateIn.minusDays(days)
}
fun getShortDate (dateIn: ZonedDateTime): String {
    var shortDate = dateIn.year.toString() + "-" + dateIn.monthValue.toString() + "-" + dateIn.dayOfMonth.toString()
    var test = dateIn.month
    return shortDate
}
