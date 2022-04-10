package top.shuzz.epub.library

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyEpubLibraryApplication

fun main(args: Array<String>) {
	runApplication<MyEpubLibraryApplication>(*args)
}
