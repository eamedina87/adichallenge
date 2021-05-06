package tech.medina.adichallenge.data.mapper

interface IMapper<O, D> {
    fun map(input: O): D
}