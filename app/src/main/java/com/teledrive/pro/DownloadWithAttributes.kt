package com.teledrive.pro
import android.content.ContentValues
import android.content.Context
import android.provider.MediaStore
import java.io.File
/**
 * DOWNLOAD FILE & FOLDER + PRESERVE ATRIBUT
 * 1. Download file -> File.setLastModified(originalDate)
 * 2. Simpan ke MediaStore dengan DATE_MODIFIED + DATE_TAKEN = tanggal asli
 *    -> Gallery tidak berubah jadi tanggal download, tetap tanggal foto asli.
 * 3. Download folder = loop semua file di folder, preserve satu-satu.
 */
object DownloadWithAttributes {
    fun preserveFile(file: File, originalMillis: Long) { try { file.setLastModified(originalMillis) } catch (_: Exception) {} }
    fun saveToGallery(ctx: Context, file: File, originalMillis: Long, mime: String = "image/jpeg") {
        val values = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, file.name)
            put(MediaStore.MediaColumns.MIME_TYPE, mime)
            put(MediaStore.MediaColumns.DATE_MODIFIED, originalMillis / 1000)
            put(MediaStore.MediaColumns.DATE_TAKEN, originalMillis)
            put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/TeleDrive Pro")
        }
        ctx.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)?.let { uri ->
            ctx.contentResolver.openOutputStream(uri)?.use { out -> file.inputStream().copyTo(out) }
        }
    }
    suspend fun downloadFolder(files: List<Pair<File, Long>>, onProgress: (Int, Int) -> Unit) {
        files.forEachIndexed { i, (f, date) -> preserveFile(f, date); onProgress(i + 1, files.size) }
    }
}
