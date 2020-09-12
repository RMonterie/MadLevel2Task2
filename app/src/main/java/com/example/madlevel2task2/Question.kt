package com.example.madlevel2task2

data class Question (
    var query: String,
    var answer: Boolean
) {
    companion object {
        val QUERIES = arrayOf(
            "A 'val' and 'var' are the same.",
            "Kotlin 'when' replaces the 'switch' operator in Java.",
            "Formerly, Mobile Application Development was taught using Java",
            "You can use the keywords 'const' and 'let' in Kotlin"
        )

        val ANSWERS = arrayOf(
            false,
            true,
            true,
            false
        )
    }
}