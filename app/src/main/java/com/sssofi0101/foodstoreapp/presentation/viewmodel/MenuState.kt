package com.sssofi0101.foodstoreapp.presentation.viewmodel

class MenuState private constructor(val status: Status, val msg: String? = null) {
    companion object {
        val LOADED = MenuState(Status.SUCCESS)
        val LOADING = MenuState(Status.LOADING)
        fun error(msg: String?) = MenuState(Status.FAILED, msg)
    }

    enum class Status {
        LOADING,
        SUCCESS,
        FAILED
    }
}