package com.example.apptodo.di

import android.app.Application
import androidx.room.Room
import com.example.apptodo.data.local.TaskDatabase
import com.example.apptodo.data.local.TaskDatabase.Companion.DATABASE_NAME
import com.example.apptodo.data.repository.TaskRepositoryImpl
import com.example.apptodo.domain.repository.TaskRepository
import com.example.apptodo.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTaskDB(app: Application): TaskDatabase {
        return Room.databaseBuilder(app, TaskDatabase::class.java, DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideTaskUseCase(repository: TaskRepository): TaskUseCase {
        return TaskUseCase(
            getAllTasks = GetAllTasks(repository),
            getTaskById = GetTaskById(repository),
            insertTask = InsertTask(repository),
            deleteTask = DeleteTask(repository),
            updateTask = UpdateTask(repository)
        )
    }

    @Provides
    @Singleton
    fun provideRepository(db: TaskDatabase): TaskRepository {
        return TaskRepositoryImpl(db.getTaskDao())
    }
}