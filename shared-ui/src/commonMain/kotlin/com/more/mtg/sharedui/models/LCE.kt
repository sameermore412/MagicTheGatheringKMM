package com.more.mtg.sharedui.models

sealed class LCE<T> {
    class Loading<T>: LCE<T>()
    class Content<T>(val data: T) : LCE<T>()
    class Error<T>(val message: String): LCE<T>()
}