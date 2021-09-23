package com.example.foodrecipeapp.network

import com.example.foodrecipeapp.data.LocalDataSource
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