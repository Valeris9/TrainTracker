package com.example.traintrack.PaginaPrincipal

import androidx.annotation.Dimension
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import java.util.jar.Manifest


@Composable
fun MapScreen() {
    // Coordenadas de la casa en el mapa
    val house = LatLng(41.636253937203136, 2.299580470814943)

    // Estado para almacenar la posición de la cámara del mapa
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(house, 10f)
    }

    // Vista de Android para mostrar el mapa
    AndroidView(
        factory = { context ->
            MapView(context).apply {
                // Configurar el mapa
                onCreate(null)
                getMapAsync { googleMap ->
                    // Configurar la API key y manejar excepciones
                    try {
                        MapsInitializer.initialize(context.applicationContext)
                    } catch (e: GooglePlayServicesNotAvailableException) {
                        // Manejar cualquier excepción si Google Play Services no está disponible
                        e.printStackTrace()
                    }

                    // Mostrar el mapa y agregar un marcador en la posición de la casa
                    googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPositionState.position))
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(house)
                            .title("Casa")
                            .snippet("Casa Victor")
                    )
                }
            }
        },
        modifier = Modifier.fillMaxWidth()
            .height(500.dp)
    )
}

