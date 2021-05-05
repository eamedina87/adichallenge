package tech.medina.adichallenge.data.mapper

interface Mapper<O, D> {
    fun map(input: O): D
}