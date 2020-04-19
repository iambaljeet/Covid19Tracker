package com.app.covid19tracker.db

fun provideAllDataDao(allPostsDatabase: AllPostsDatabase): DatabaseDao {
    return allPostsDatabase.getAllPostsDao()
}