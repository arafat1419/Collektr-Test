package com.arafat1419.collektr.data.network.source.auction

import android.content.Context
import com.arafat1419.collektr.data.network.api.ApiResponse
import com.arafat1419.collektr.data.network.model.auction.AuctionCategoryResponse
import com.arafat1419.collektr.data.network.model.auction.AuctionResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class AuctionMockSourceImpl(
    private val context: Context,
    private val gson: Gson
) : AuctionMockSource {

    override suspend fun getAuctions(categoryId: Int): Flow<ApiResponse<List<AuctionResponse>>> =
        mockHandler {
            val jsonFileString = getJsonDataFromAsset("auctions.json")
            val listType = object : TypeToken<List<AuctionResponse>>() {}.type
            val auctions: List<AuctionResponse> = gson.fromJson(jsonFileString, listType)
            val filteredAuctions = auctions.filter { it.category.id == categoryId }

            filteredAuctions.ifEmpty {
                throw Exception("No auctions found for category ID: $categoryId")
            }
        }

    override suspend fun getAuctionDetails(auctionId: Int): Flow<ApiResponse<AuctionResponse>> =
        mockHandler {
            val jsonFileString = getJsonDataFromAsset("auctions.json")
            val listType = object : TypeToken<List<AuctionResponse>>() {}.type
            val auctions: List<AuctionResponse> = gson.fromJson(jsonFileString, listType)
            val auction = auctions.firstOrNull { it.id == auctionId }
            auction ?: throw Exception("Auction not found for ID: $auctionId")
        }

    override suspend fun getAuctionCategories(): Flow<ApiResponse<List<AuctionCategoryResponse>>> =
        mockHandler {
            val jsonFileString = getJsonDataFromAsset("auction_categories.json")
            val listType = object : TypeToken<List<AuctionCategoryResponse>>() {}.type
            val categories: List<AuctionCategoryResponse> = gson.fromJson(jsonFileString, listType)

            categories.ifEmpty {
                throw Exception("No categories found")
            }
        }

    private fun getJsonDataFromAsset(fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }

    private suspend fun <T> mockHandler(call: suspend () -> T): Flow<ApiResponse<T>> = flow {
        try {
            val response = call()
            emit(ApiResponse.Success(response))
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
        }
    }
}