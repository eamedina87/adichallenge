package tech.medina.adichallenge.ui.utils

fun String.sanitize(): String {
    val queryWithEscapedQuotes = this.replace(Regex.fromLiteral("\""), "\"\"")
    return "*\"$queryWithEscapedQuotes\"*"
}