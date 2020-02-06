package com.example.appcommerce.model

data class Product (
    val id: String,
    val title: String,
    val category: ProductCategory,
    val description: String,
    val price: Double,
    val color: List<ProductColor>,
    val size: List<ProductSize>,
    val images: List<ProductImage>
)