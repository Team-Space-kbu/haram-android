package com.space.builder_annotation.annotation

@Target(AnnotationTarget.CLASS)
annotation class Validation

@Target(AnnotationTarget.FIELD)
annotation class Regex(
    val regex: String,
    val tag: String
)