package com.app.covid19tracker.utility

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.text.format.DateFormat.format
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatImageView
import com.app.covid19tracker.R
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

fun getFormattedDate(
    date: String,
    inputDateFormat: String? = null,
    outputStringFormat: String? = null
): String {
    val format = SimpleDateFormat(inputDateFormat, Locale.getDefault())
    val newDate = format.parse(date)
    return formatDateToString(
        newDate,
        outputStringFormat
    )
}

fun formatDateToString(date: Date?, outputStringFormat: String? = null): String {
    return format(outputStringFormat, date).toString()
}

fun Resources.isSystemDarkThemeEnabled(): Boolean {
    return when (configuration.uiMode) {
        Configuration.UI_MODE_NIGHT_YES -> {
            true
        }
        Configuration.UI_MODE_NIGHT_NO -> {
            false
        }
        else -> {
            false
        }
    }
}

fun Context.getApplicationTheme(): Int {
    return if (isApplicationThemeEnabled()) {
        getAppTheme()
    } else {
        when (resources.configuration.uiMode) {
            Configuration.UI_MODE_NIGHT_YES -> {
                AppCompatDelegate.MODE_NIGHT_YES
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                AppCompatDelegate.MODE_NIGHT_NO
            }
            else -> {
                AppCompatDelegate.MODE_NIGHT_NO
            }
        }
    }
}

fun Context.isApplicationThemeEnabled(): Boolean {
    return getAppTheme() != -100
}

fun setAppTheme(theme: Int) {
    AppCompatDelegate.setDefaultNightMode(theme)
}

fun Context.getAppTheme(): Int {
    val sharedPreference = getSharedPreference()
    return sharedPreference?.getInt(Constants.THEME_SETTING, -100) ?: -100
}

fun Context.getSharedPreference(): SharedPreferences? {
    return getSharedPreferences(Constants.SHARED_PREFS, MODE_PRIVATE)
}

fun AppCompatImageView.toggleBasedOnTheme(theme: Int) {
    when (theme) {
        AppCompatDelegate.MODE_NIGHT_YES -> {
            setImageResource(R.drawable.ic_moon_icon)
        }
        else -> {
            setImageResource(R.drawable.ic_sun_icon)
        }
    }
    setAppTheme(theme)
}

inline fun <reified T> fromJson(value: String): T {
    val gson = Gson()
    return gson.fromJson(
        value,
        T::class.java
    )
}

inline fun <reified T> toJson(value: T): String {
    val gson = Gson()
    return gson.toJson(value)
}