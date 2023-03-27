package com.bykov.footea.usecase

fun interface UseCase<out Type, in Params> {

    operator fun invoke(params: Params): Type
}

fun interface SimpleUseCase<out Type> {

    operator fun invoke(): Type
}