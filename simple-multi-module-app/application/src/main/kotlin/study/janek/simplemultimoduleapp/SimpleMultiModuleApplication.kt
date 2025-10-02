package study.janek.simplemultimoduleapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SimpleMultiModuleApplication

fun main(args: Array<String>) {
    runApplication<SimpleMultiModuleApplication>(*args)
}
