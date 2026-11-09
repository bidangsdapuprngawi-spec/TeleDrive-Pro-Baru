package com.teledrive.pro
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val demo = GalleryGrouper.groupByMonthYear(listOf(
            GalleryItem("1","Foto 1", 1756000000000L), GalleryItem("2","Foto 2", 1753000000000L), GalleryItem("3","Foto 3", 1720000000000L)
        ))
        setContent {
            MaterialTheme {
                Scaffold(topBar = { TopAppBar(title = { Text("TeleDrive Pro — Baru") }) }) { pad ->
                    Column(Modifier.padding(pad).padding(16.dp)) {
                        Text("Multi-Akun, Download Folder + Preserve Tanggal, Gallery Bulan-Tahun", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(12.dp))
                        Text("Demo Gallery (data asli akan dari Telegram setelah login):")
                        LazyColumn { demo.forEach { g -> stickyHeader { Surface(color = MaterialTheme.colorScheme.primaryContainer) { Text(g.title, Modifier.padding(8.dp)) } }; items(g.items) { Text("• ${it.name}", Modifier.padding(8.dp)) } } }
                    }
                }
            }
        }
    }
}
