package fa.market

import io.ktor.util.PlatformUtils
import mu.KotlinLogging

//import io.ktor.util.isAssertionEnabled


object KtorUtil {
//    public actual val IS_DEVELOPMENT_MODE: Boolean =
//        System.getProperty(DEVELOPMENT_MODE_KEY)?.toBoolean() ?: isAssertionEnabled()

    private val logger = KotlinLogging.logger {}

    private const val DEVELOPMENT_MODE_KEY: String = "io.ktor.development"

    val isAssertionEnabled: Boolean get() = PlatformUtils::class.java.desiredAssertionStatus()

    val devModeSysProp: String? get() = System.getProperty(DEVELOPMENT_MODE_KEY)

    val isDevMode: Boolean get() = devModeSysProp?.toBoolean() ?: isAssertionEnabled
    
    val all:String get() = "devModeSysProp: $devModeSysProp isAssertionEnabled: $isAssertionEnabled  isDevMode: $isDevMode"
    
    fun dump(){
        logger.warn { all }
    }

    
    
    
}