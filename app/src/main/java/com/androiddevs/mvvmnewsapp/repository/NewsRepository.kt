package com.androiddevs.mvvmnewsapp.repository

import com.androiddevs.mvvmnewsapp.api.NewsAPI
import com.androiddevs.mvvmnewsapp.data.Article
import com.androiddevs.mvvmnewsapp.data.local.ArticleDao
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val articleDao: ArticleDao,
    private val newsAPI: NewsAPI
) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        newsAPI.getBreakingNews(countryCode, pageNumber)


    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        newsAPI.searchNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = articleDao.upsert(article)

    suspend fun deleteArticle(article: Article) = articleDao.deleteArticle(article)

    fun getSavedNews() = articleDao.getArticles()
}