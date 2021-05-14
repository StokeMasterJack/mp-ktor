package ss.kutil

import java.io.File
import java.util.logging.Logger

enum class PropKey(val ser: String) {
    UserName("user.name"),
    HomeDir("user.home"),
    WorkDir("user.dir"),
    TmpDir("java.io.tmpdir"),
    OsName("os.name"),
    FileSep("file.separator"),
    PathSep("path.separator"),
    LineSep("line.separator");

    override fun toString() = ser

}


object Sys {

    fun env(key: String): String = System.getenv(key)
    fun prop(key: String): String = System.getProperty(key)
    fun prop(key: PropKey): String = prop(key.ser)

    fun logProps(label: String = "SysProps", logger: Logger = propLogger) {

        with(logger) {
            info("${label}:")
            PropKey.values().forEach {
                val key = it
                val value = prop(key)
                info("  ${key}[${value}]")
            }
        }
    }


    val homeDirPath: String get() = prop(PropKey.HomeDir)
    val userHome: String get() = prop(PropKey.HomeDir)
    val homeDir: File get() = File(homeDirPath)
    val downloads: File get() = File("${userHome}/Downloads")
    val tmpDir: File get() = File(prop(PropKey.TmpDir))
    val userName: String get() = prop(PropKey.UserName)
    val osName: String get() = prop(PropKey.OsName)
    val fileSep: String get() = prop(PropKey.FileSep)
    val pathSep: String get() = prop(PropKey.PathSep)
    val lineSep: String get() = prop(PropKey.LineSep)

    val isMac: Boolean get() = osName.contains("Mac OS X")
    val isDavesMac: Boolean get() = isMac && userHome == "/Users/dford"

    private val propLogger = Logger.getLogger("SysProps")
}

