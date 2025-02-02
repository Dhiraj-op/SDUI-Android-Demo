package com.example.sdui.data

data class UiComponent(
    val type :String,  // What kind of component (e.g., "card", "header")
    val properties: Map<String,Any>, // Component-specific data
)

data class FeaturedContent(
    val layout: List<UiComponent>
)
