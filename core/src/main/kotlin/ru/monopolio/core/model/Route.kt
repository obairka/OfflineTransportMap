package ru.monopolio.core.model


class Route(
        val name: String,
        val transportType: TransportType,
        val array: Array<Station>,
        val isCircular: Boolean,
        val isBiDirectional: Boolean) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Route

        if (name != other.name) return false
        if (transportType != other.transportType) return false
        if (isCircular != other.isCircular) return false
        if (isBiDirectional != other.isBiDirectional) return false
        if (array.size != other.array.size) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + transportType.hashCode()
        result = 31 * result + isCircular.hashCode()
        result = 31 * result + isBiDirectional.hashCode()
        return result
    }

    override fun toString(): String {
        return "Route(name='$name', transportType=$transportType, isCircular=$isCircular, isBiDirectional=$isBiDirectional)"
    }


}