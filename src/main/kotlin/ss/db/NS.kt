@file:Suppress("MemberVisibilityCanBePrivate")

package ss.db

enum class NS(val code: String) {

    System("s"),
    App("s");

    override fun toString() = name
}

data class FieldId(val recordType: RecordType.Id, val name: String)

/**
 * AKA PK
 */
@JvmInline
value class Id(val value: String)

/**
 * aka FK
 */
data class Ref(val recordType: RecordType.Id, val id: Id)


fun NS.typeId(code: RecordType.Code): RecordType.Id = RecordType.Id(this, code)
fun RecordType.Id.fieldId(fieldName: String): FieldId = FieldId(this, fieldName)
fun RecordType.Id.ref(id: Id): Ref = Ref(this, id)

data class Field<T>(
    val name: String,
    val filter: Boolean,
    val sort: Boolean,
    val default: T?,
    val nullable: Boolean,
    val recordType: RecordType.Id,
) {
    init {

        if (!nullable) {
            require(default != null) { "Non-nullable fields cannot have a null default value" }
        }
    }


    val id: FieldId = recordType.fieldId(name)


    companion object {

    }


}


/**
 * Record type (aka table def)
 */
data class RecordType(
    val ns: NS,
    val code: Code,
    val name: String,
    val id: Id = Id(ns, code),
    val fields: List<Field<*>>,
) {


//    val id: Id = Id(ns, code)

    /**
     * AKA PK
     */
    @JvmInline
    value class Code(val valueRaw: String?) {

        init {
            requireNotNull(valueRaw)
            require(valueRaw.length <= 3) {
                "valueRaw[$valueRaw]  valueRaw.length[${valueRaw.length}]"
            }
        }

        val value: String get() = requireNotNull(valueRaw)
        val valuePadded: String get() = requireNotNull(value.padStart(3))

        override fun toString() = value


    }

    data class Id(val ns: NS, val code: Code) {
        companion object {
            fun mk(ns: NS, codeStr: String): Id = Id(ns, Code(codeStr))
        }

        override fun toString(): String {
            return "${ns.code}:${code}"
        }
    }

    class Builder {
        var ns: NS = NS.App
        var codeStr: String? = null
        var name: String? = null

        val fields: MutableList<Field<*>> = mutableListOf()

        inline fun <reified T> add(field: Field<T>) {
            fields.add(field)
        }


        inline fun <reified T> field(
            name: String,
            filter: Boolean = true,
            sort: Boolean = true,
            default: T? = null,
            nullable: Boolean = true
        ) {
            val code: Code = Code(codeStr)
            val recordType: Id = Id(ns, code)

            val field: Field<T> = Field<T>(
                name = name,
                filter = filter,
                sort = sort,
                default = default,
                nullable = nullable,
                recordType = recordType
            )

            add(field)

        }

        fun build(): RecordType {
            val code = Code(codeStr)
            val name = checkNotNull(name)

            return RecordType(
                ns = ns,
                code = code,
                name = name,
                fields = fields
            )
        }


    }

    companion object {
        fun build(block: Builder.() -> Unit): RecordType {
            val b = Builder()
            b.block()
            return b.build()
        }

        fun fixCode(codeStr: String?): Code {
            val codeStrFixed = requireNotNull(codeStr)
            return Code(codeStrFixed)
        }
    }


}


data class Types(val types: List<RecordType.Id>) {

    fun filter(ns: NS): List<RecordType.Id> = types.filter { it.ns == ns }
    val app: List<RecordType.Id> get() = filter(NS.App)
    val sys: List<RecordType.Id> get() = filter(NS.System)
}


fun main() {
    val rt: RecordType = RecordType.build {
        ns = NS.System
        codeStr = "rt"
        name = "RecordType"


        field<Int>("age", filter = true, sort = true)
        field<Int>("size")
    }

    println(rt)
}



