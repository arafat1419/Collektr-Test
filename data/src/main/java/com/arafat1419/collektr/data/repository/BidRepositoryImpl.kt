package com.arafat1419.collektr.data.repository

import com.arafat1419.collektr.data.local.model.bid.BidEntity
import com.arafat1419.collektr.data.local.source.bid.BidLocalSource
import com.arafat1419.collektr.data.mapper.MapperDomainExtensions.toBidEntity
import com.arafat1419.collektr.data.mapper.MapperEntityExtensions.toDomain
import com.arafat1419.collektr.domain.model.chatbid.ChatBid
import com.arafat1419.collektr.domain.repository.BidRepository
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class BidRepositoryImpl(private val bidLocalSource: BidLocalSource) : BidRepository {
    override suspend fun getAuctionBids(auctionId: Int): Flow<Resource<List<ChatBid>>> =
        NetworkBoundResource<List<ChatBid>, List<BidEntity>>(
            shouldFetch = { false },
            loadFromDB = {
                bidLocalSource.getAuctionBids(auctionId).map {
                    it.map { bidEntity -> bidEntity.toDomain() }
                }
            }
        ).asFlow()

    override suspend fun getHighestBid(auctionId: Int): Flow<Resource<ChatBid>> =
        NetworkBoundResource<ChatBid, BidEntity?>(
            shouldFetch = { false },
            loadFromDB = {
                bidLocalSource.getHighestBid(auctionId).map {
                    it?.toDomain() ?: ChatBid(auctionId = auctionId)
                }
            }
        ).asFlow()


    override suspend fun insertBid(chatBid: ChatBid) {
        bidLocalSource.insertBid(chatBid.toBidEntity())
    }
}