package com.tmdbapp.mylearning.extensions

import com.tmdbapp.mylearning.mapper.MovieView
import com.tmdbapp.mylearning.model.Movie
import java.text.SimpleDateFormat
import java.util.*


fun String?.toDateInMMMDYYYYFormat(): String {
    val INPUT_DATE_FORMAT = "yyyy-mm-dd"
    val OUTPUT_DATE_FORMAT = "MMM d, yyyy"
    val mParsedDate: Date
    var mOutputDateString = "NA"
    val mInputDateFormat =
        SimpleDateFormat(INPUT_DATE_FORMAT, Locale.getDefault())
    val mOutputDateFormat =
        SimpleDateFormat(OUTPUT_DATE_FORMAT, Locale.getDefault())
    this?.let {
        mParsedDate = mInputDateFormat.parse(this)
        mOutputDateString = mOutputDateFormat.format(mParsedDate).toString()
    }
    return mOutputDateString
}

fun Movie.toMovieView() = MovieView(
    name = title,
    description = overview,
    releaseDate = release_date,
    movieId = id,
    thumbnail = poster_path
)