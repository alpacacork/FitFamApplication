package com.brentcodes.fitfamapplication.repo

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideDatabaseRepository(): DatabaseRepository {
        return DatabaseRepositoryImpl()
    }

}