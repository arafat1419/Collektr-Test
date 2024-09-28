package com.arafat1419.collektr.data.repository

import com.arafat1419.collektr.data.local.source.auction.AuctionLocalSource
import com.arafat1419.collektr.data.mapper.MapperEntityExtensions.toDomain
import com.arafat1419.collektr.data.mapper.MapperResponseExtensions.toDomain
import com.arafat1419.collektr.data.mapper.MapperResponseExtensions.toEntity
import com.arafat1419.collektr.data.network.source.auction.AuctionMockSource
import com.arafat1419.collektr.domain.model.auction.Auction
import com.arafat1419.collektr.domain.model.auction.AuctionCategory
import com.arafat1419.collektr.domain.repository.AuctionRepository
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class AuctionRepositoryImpl(
    private val auctionMockSource: AuctionMockSource,
    private val auctionLocalSource: AuctionLocalSource
) : AuctionRepository {
    override suspend fun getAuctions(categoryId: Int): Flow<Resource<List<Auction>>> =
        NetworkBoundResource(
            shouldFetch = { it.isNullOrEmpty() },
            createCall = { auctionMockSource.getAuctions(categoryId) },
            processResponse = { response -> response.map { it.toDomain() } },
            loadFromDB = {
                auctionLocalSource.getAuctionsByCategory(categoryId).map {
                    it.map { auctionEntity -> auctionEntity.toDomain() }
                }
            },
            saveCallResult = { auctions ->
                auctions.forEach { auction ->
                    auctionLocalSource.insertAuction(auction.toEntity())
                }
            }
        ).asFlow()

    override suspend fun getAuctionDetails(auctionId: Int): Flow<Resource<Auction>> =
        NetworkBoundResource(
            shouldFetch = { it == null },
            createCall = { auctionMockSource.getAuctionDetails(auctionId) },
            processResponse = { response -> response.toDomain() },
            loadFromDB = {
                auctionLocalSource.getAuctionDetails(auctionId).map {
                    it.toDomain()
                }
            },
            saveCallResult = { auction ->
                auctionLocalSource.insertAuction(auction.toEntity())
            }
        ).asFlow()

    override suspend fun getAuctionCategories(): Flow<Resource<List<AuctionCategory>>> =
        NetworkBoundResource(
            shouldFetch = { true },
            createCall = { auctionMockSource.getAuctionCategories() },
            processResponse = { response -> response.map { it.toDomain() } },
        ).asFlow()
}