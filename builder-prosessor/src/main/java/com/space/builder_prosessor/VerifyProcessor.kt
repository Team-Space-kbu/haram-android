package com.space.builder_prosessor

import com.google.auto.service.AutoService
import com.space.builder_annotation.FieldNameAndTag
import com.space.builder_annotation.annotation.Validation
import com.squareup.kotlinpoet.FileSpec
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.TypeElement
import com.space.builder_annotation.annotation.Regex
import com.space.builder_annotation.ValidationResult
import com.squareup.kotlinpoet.DelicateKotlinPoetApi
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.asTypeName
import java.io.File
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.Name
import javax.tools.Diagnostic

@AutoService(Processor::class)
class VerifyProcessor : AbstractProcessor() {
    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
        val fileBuilder = FileSpec.builder("com.space.generated", "ValidationExtension")
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(
            Validation::class.java.name,
            Regex::class.java.name
        )
    }

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latestSupported()

    override fun process(
        annotations: MutableSet<out TypeElement>?,
        roundEnv: RoundEnvironment
    ): Boolean {

        val classElements = roundEnv.getElementsAnnotatedWith(Validation::class.java)

        if (!checkElementType(ElementKind.CLASS, classElements)) return false

        classElements.forEach { fileBuilder.addFunction(makeValidateFunction(it)) }

        fileBuilder.addImport(FieldNameAndTag::class.java, "")
        val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME]
        fileBuilder.build().writeTo(File(kaptKotlinGeneratedDir.toString()))
        return true
    }

    @OptIn(DelicateKotlinPoetApi::class)
    private fun makeValidateFunction(classElement: Element): FunSpec {
        val validateFunSpec = FunSpec.builder("validate")
            .receiver(classElement.asType().asTypeName())
            .returns(ValidationResult::class)
            .addStatement("val result = %T()", ValidationResult::class.java)

        val fieldElement = classElement.enclosedElements
        fieldElement.forEach {
            val regex = it.getAnnotation(Regex::class.java)
            regex?.let { anno ->
                validateFunSpec.addComment("Regex Match Check")
                validateFunSpec.beginControlFlow("if(${it.simpleName} == null || !%S.toRegex().matches(${it.simpleName}))", anno.regex)
                validateFunSpec.addStatement("result.isValid = false")
                validateFunSpec.addStatement("result.invalidFieldNameAndTags.add(${createFieldNameAndTag(it.simpleName, anno.tag)})")
                validateFunSpec.endControlFlow()
            }
        }
        return validateFunSpec.addStatement("return result").build()
    }

    private fun checkElementType(kind: ElementKind, elements: Set<Element>): Boolean {
        if (elements.isEmpty()) return false

        elements.forEach {
            if (it.kind != kind) {
                printMessage(
                    Diagnostic.Kind.ERROR, "Only ${kind.name} Are Supported", it
                )
                return false
            }
        }
        return true
    }

    private fun printMessage(kind: Diagnostic.Kind, message: String, element: Element) {
        processingEnv.messager.printMessage(kind, message, element)
    }

    private fun createFieldNameAndTag(fieldName: Name, tag: String): String {
        return "FieldNameAndTag(\"$fieldName\", \"$tag\")"
    }

}