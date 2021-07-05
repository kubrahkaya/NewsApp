package com.androiddevs.mvvmnewsapp.di

import android.content.Context
import androidx.room.Room
import com.androiddevs.mvvmnewsapp.api.NewsAPI
import com.androiddevs.mvvmnewsapp.data.local.ArticleDao
import com.androiddevs.mvvmnewsapp.data.local.ArticleDatabase
import com.androiddevs.mvvmnewsapp.repository.NewsRepository
import com.androiddevs.mvvmnewsapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    //todo try to add interceptors
    @Singleton
    @Provides
    fun provideNewsApi(): NewsAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(NewsAPI::class.java)
    }


    @Singleton
    @Provides
    fun provideArticleDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            ArticleDatabase::class.java,
            "article_db.db"
        ).build()

    @Singleton
    @Provides
    fun provideArticleDao(database: ArticleDatabase) = database.getArticleDao()

    @Singleton
    @Provides
    fun provideRepository(dao: ArticleDao, api: NewsAPI) = NewsRepository(dao, api)
}