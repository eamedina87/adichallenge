package tech.medina.adichallenge.ui.utils

fun String.sanitize(): String {
    return "*$this*"
}