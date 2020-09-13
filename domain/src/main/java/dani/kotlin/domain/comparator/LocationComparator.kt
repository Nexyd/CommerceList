package dani.kotlin.domain.comparator

import dani.kotlin.domain.entities.CommerceInfo
import kotlin.math.*

class LocationComparator(
    private val userLatitude:  Double,
    private val userLongitude: Double
) : Comparator<CommerceInfo> {

    override fun compare(pos1: CommerceInfo?, pos2: CommerceInfo?): Int {
        var result = 2147483647
        if (pos1?.latitude != null && pos1.longitude != null &&
            pos2?.latitude != null && pos2.longitude != null) {

            val distanceToPlace1 = distance(pos1.latitude,
                pos1.longitude, userLatitude, userLongitude)

            val distanceToPlace2 = distance(pos2.latitude,
                pos2.longitude, userLatitude, userLongitude)

            result = (distanceToPlace1 - distanceToPlace2).toInt()
        }

        return result
    }

    private fun distance(
        fromLat: Double, fromLon: Double,
        toLat: Double, toLon: Double
    ): Double {
        val radius = 6378137.0 // approximate Earth radius, *in meters*
        val deltaLat = toLat - fromLat
        val deltaLon = toLon - fromLon
        val angle = 2 * asin(
            sqrt(
                sin(deltaLat / 2).pow(2.0) +
                        cos(fromLat) * cos(toLat) *
                        sin(deltaLon / 2).pow(2.0))
        )

        return radius * angle
    }
}