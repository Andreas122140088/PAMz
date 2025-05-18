package com.hadiyarajesh.composetemplate.repository

import com.hadiyarajesh.composetemplate.data.dao.ImageDao
import com.hadiyarajesh.composetemplate.data.entity.Image
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

interface HomeRepository {
    fun loadData(): Flow<Image?>
    suspend fun loadDataFromFirebase(): Image?
}

@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val imageDao: ImageDao,
    private val firebaseDatabaseRepository: FirebaseDatabaseRepository
) : HomeRepository {
    override fun loadData(): Flow<Image?> {
        return imageDao.getImages()
    }

    override suspend fun loadDataFromFirebase(): Image? {
        return try {
            firebaseDatabaseRepository.readData("home/image", Image::class.java)
        } catch (e: Exception) {
            null
        }
    }
}
