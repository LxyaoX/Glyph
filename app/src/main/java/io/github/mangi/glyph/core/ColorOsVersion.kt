package io.github.mangi.glyph.core

import android.annotation.SuppressLint

/**
 * 系统 ColorOS 主版本号。
 *
 * ColorOS 15 之后 `OplusBuild.getOplusOSVERSION()` 返回的是 API 版本，不再是版本下标，
 * 所以数字版本统一取自 `ro.build.version.oplusrom`，例如 `V17.0.1` 取 17。
 */
internal object ColorOsVersion {

    /** ColorOS 主版本号，非 ColorOS 或读取失败时为 0 */
    val number: Int by lazy { parseMajorVersion(readProperty(ROM_VERSION_PROPERTY)) }

    /** 是否 ColorOS 17 及以上 */
    val isAtLeast17: Boolean get() = number >= 17

    @SuppressLint("PrivateApi") // 系统属性接口不对外公开，只有系统进程可读。
    private fun readProperty(key: String): String? = try {
        val properties = Class.forName("android.os.SystemProperties")
        (properties.getMethod("get", String::class.java).invoke(null, key) as? String)
            ?.takeIf(String::isNotBlank)
    } catch (_: Throwable) {
        null
    }

    private const val ROM_VERSION_PROPERTY = "ro.build.version.oplusrom"
}

/** 从 `V17.0.1` 这类 ROM 版本串取主版本号，格式不符时返回 0 */
internal fun parseMajorVersion(raw: String?): Int =
    raw?.removePrefix("V")?.substringBefore('.')?.toIntOrNull() ?: 0
