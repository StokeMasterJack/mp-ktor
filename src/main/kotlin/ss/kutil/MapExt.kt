package ss.kutil

import kotlin.reflect.KProperty

fun <V> Map<String, V>.getValue(p: KProperty<*>): V = getValue(p.name)

inline fun <reified K, reified V> Map<K, V>.trimValues(): Map<K, V> = this.mapValues<K, V, V> {
    val v = it.value
    if (v is String) v.trim() as V else v
}

inline fun <reified K, reified V> Map<K, V>.trimKeys(): Map<K, V> = this.mapKeys<K, V, K> {
    val k = it.key
    if (k is String) k.trim() as K else k
}

inline fun <reified K, reified V> Map<K, V>.trim(): Map<K, V> = trimKeys().trimValues()