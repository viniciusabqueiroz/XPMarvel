package com.example.xpmarvel.data.local

object RoomTransaction {
    fun execute(db: MarvelDB, block: () -> Unit) {
        db.runInTransaction(block)
    }
}