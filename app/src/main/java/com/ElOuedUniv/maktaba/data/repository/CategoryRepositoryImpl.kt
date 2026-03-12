package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Category

class CategoryRepositoryImpl : CategoryRepository {

    private val categoriesList = listOf(
        Category("1", "Programming", "Books about software development and coding"),
        Category("2", "Algorithms", "Books about algorithms and data structures"),
        Category("3", "Databases", "Books about database design and management"),
        Category("4", "Mobile Development", "Books about Android and iOS development"),
        Category("5", "Artificial Intelligence", "Books about AI, Machine Learning, and Deep Learning")
    )

    override fun getAllCategories(): List<Category> = categoriesList

    override fun getCategoryById(id: String): Category? =
        categoriesList.find { it.id == id }
}