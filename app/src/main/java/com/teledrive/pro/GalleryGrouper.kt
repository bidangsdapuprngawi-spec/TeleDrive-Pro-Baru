package com.teledrive.pro
import java.text.SimpleDateFormat
import java.util.*
data class GalleryItem(val id: String, val name: String, val dateMillis: Long)
data class GalleryMonthGroup(val title: String, val count: Int, val items: List<GalleryItem>)
/**
 * GALLERY BULAN + TAHUN seperti Google Photos.
 * Contoh: "Agustus 2025 (124 foto)" -> sticky header.
 */
object GalleryGrouper {
    private val fmt = SimpleDateFormat("MMMM yyyy", Locale("id", "ID"))
    fun groupByMonthYear(items: List<GalleryItem>): List<GalleryMonthGroup> {
        return items.sortedByDescending { it.dateMillis }
            .groupBy { fmt.format(Date(it.dateMillis)) }
            .map { (title, list) -> GalleryMonthGroup(title = "$title (${list.size} foto)", count = list.size, items = list) }
    }
}
