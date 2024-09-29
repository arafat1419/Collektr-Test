package com.arafat1419.collektr.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arafat1419.collektr.data.local.model.chat.ChatEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface ChatDao {
    @Query("SELECT * FROM auction_chat WHERE auction_id = :auctionId ORDER BY created_at DESC")
    fun getAuctionChats(auctionId: Int): Flow<List<ChatEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChat(chat: ChatEntity)
}