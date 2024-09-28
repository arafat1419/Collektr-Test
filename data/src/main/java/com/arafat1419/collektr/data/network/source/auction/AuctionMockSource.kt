package com.arafat1419.collektr.data.network.source.auction

import com.arafat1419.collektr.data.network.api.ApiResponse
import com.arafat1419.collektr.data.network.model.auction.AuctionCategoryResponse
import com.arafat1419.collektr.data.network.model.auction.AuctionResponse
import kotlinx.coroutines.flow.Flow

internal interface AuctionMockSource {
    suspend fun getAuctions(categoryId: Int): Flow<ApiResponse<List<AuctionResponse>>>
    suspend fun getAuctionDetails(auctionId: Int): Flow<ApiResponse<AuctionResponse>>
    suspend fun getAuctionCategories(): Flow<ApiResponse<List<AuctionCategoryResponse>>>
}