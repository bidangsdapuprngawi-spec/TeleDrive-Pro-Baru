package com.teledrive.pro
import kotlinx.serialization.Serializable
@Serializable data class AccountSession(val id: String, val phone: String, val name: String, val apiId: Int, val apiHash: String, val isActive: Boolean = false)
// MULTI-AKUN: disimpan di DataStore, unlimited akun. Satu akun = satu folder TDLib sendiri.
// File lama: satu akun saja. File baru: list akun, bisa switch tanpa logout.
