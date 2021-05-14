package ss.kutil

import java.net.URL
import kotlin.reflect.KClass


/**
 * ClassLoader utils
 */
object CP {

    class ResourceNotFoundException(resourcePath: String) : RuntimeException(resourcePath)

    private val loader: ClassLoader
        get() {
            return Thread.currentThread().contextClassLoader
                ?: this.javaClass.classLoader
                ?: ClassLoader.getSystemClassLoader()
                ?: throw IllegalStateException("Failed to find a classloader")
        }


    @Throws(ResourceNotFoundException::class)
    fun getResource(resourcePath: String): URL {
        return loader.getResource(resourcePath) ?: throw ResourceNotFoundException("Resource[$resourcePath] not found")
    }

    @Throws(ResourceNotFoundException::class)
    fun getResource(contextClass: KClass<*>, localName: String): URL {
        val javaClass = contextClass.java
        val url = javaClass.getResource(localName)
        return url
            ?: throw ResourceNotFoundException("Resource localName[$localName] relative to contextClass[${contextClass.qualifiedName!!}] not found")
    }


    @Throws(ResourceNotFoundException::class)
    fun readText(resourcePath: String) = getResource(resourcePath).readText(Charsets.UTF_8)

    @Throws(ResourceNotFoundException::class)
    fun readText(contextClass: KClass<*>, localName: String) =
        getResource(contextClass, localName).readText(Charsets.UTF_8)
}