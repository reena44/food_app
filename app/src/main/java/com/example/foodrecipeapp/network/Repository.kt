package com.example.foodrecipeapp.network

import com.example.foodrecipeapp.LocalDataSource
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject


@ActivityRetainedScoped
class Repository @Inject constructor(

        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
) {

    val remote = remoteDataSource
    val localDataSource = localDataSource
}