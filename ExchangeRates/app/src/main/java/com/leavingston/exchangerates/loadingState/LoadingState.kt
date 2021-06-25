package com.leavingston.exchangerates.loadingState

//enum კლასი , რომ მივანიჭოთ კონკრეტული მნიშვნელობები ქოლლ ბექის მიხედვით , შეგვიძლია ბევრი სხვა მრავალპარამეტრიან მოქმედებებში გამოყენება

data class LoadingState private constructor(val status : Status , val msg : String? = null) {

    companion object {
        val LOADED = LoadingState(Status.SUCCESS)
        val LOADING = LoadingState(Status.RUNNING)
        fun error(msg: String?) = LoadingState(Status.FAILED, msg)
    }

    enum class Status {
        RUNNING,
        SUCCESS,
        FAILED
    }


}