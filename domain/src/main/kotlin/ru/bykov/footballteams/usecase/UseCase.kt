package ru.bykov.footballteams.usecase

fun interface UseCase<out Type, in Params> {

    operator fun invoke(params: Params): Type
}