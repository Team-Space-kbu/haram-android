package com.space.builder_annotation

data class ValidationResult(
    var isValid: Boolean = true,
    val invalidFieldNameAndTags: MutableList<FieldNameAndTag> = mutableListOf()
)