package cl.sanaesencia.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import android.net.Uri
import android.widget.Toast
import cl.sanaesencia.app.camera.crearArchivoFotoPerfil
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat


private val AzulHome = Color(0xFF7899BC)
private val CelesteHome = Color(0xFFEAF2F9)
private val GrafitoHome = Color(0xFF202020)

@Composable
fun PantallaHome(
    nombreUsuario: String = "Usuario"
) {
    var menuPerfilAbierto by remember { mutableStateOf(false) }


    val context = LocalContext.current

    var fotoPerfilUri by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    var uriFotoPendiente by remember {
        mutableStateOf<Uri?>(null)
    }


    val lanzadorCamara = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { guardada ->
        val pendiente = uriFotoPendiente

        if (guardada && pendiente != null) {
            fotoPerfilUri = pendiente.toString()

            Toast.makeText(
                context,
                "Foto de perfil guardada",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                context,
                "No se pudo guardar la foto",
                Toast.LENGTH_SHORT
            ).show()
        }

        uriFotoPendiente = null
    }

    fun abrirCamara() {
        val uri = crearArchivoFotoPerfil(context)
        uriFotoPendiente = uri
        lanzadorCamara.launch(uri)
    }

    val lanzadorPermiso = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { concedido ->
        if (concedido) {
            abrirCamara()
        } else {
            Toast.makeText(
                context,
                "Debes conceder el permiso para tomar una foto",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun pedirFoto() {
        val tieneCamara = context.packageManager.hasSystemFeature(
            PackageManager.FEATURE_CAMERA_ANY
        )

        if (!tieneCamara) {
            Toast.makeText(
                context,
                "Este dispositivo no tiene cámara disponible",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        val permisoConcedido = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        if (permisoConcedido) {
            abrirCamara()
        } else {
            lanzadorPermiso.launch(Manifest.permission.CAMERA)
        }
    }



    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 22.dp, vertical = 24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = CelesteHome,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Spa,
                        contentDescription = "SanaEsencia",
                        tint = AzulHome,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 12.dp)
                ) {
                    Text(
                        text = "SanaEsencia",
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold,
                        color = GrafitoHome
                    )
                    Text(
                        text = "Bienestar integral",
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                }


                Box {
                    androidx.compose.material3.IconButton(
                        onClick = {
                            if (fotoPerfilUri == null) {
                                pedirFoto()
                            } else {
                                menuPerfilAbierto = !menuPerfilAbierto
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Abrir menú de usuario",
                            tint = AzulHome,
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    DropdownMenu(
                        expanded = menuPerfilAbierto,
                        onDismissRequest = {
                            menuPerfilAbierto = false
                        }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Mi perfil") },
                            leadingIcon = {
                                Icon(Icons.Filled.Person, contentDescription = null)
                            },
                            trailingIcon = {
                                Icon(Icons.Filled.ChevronRight, contentDescription = null)
                            },
                            onClick = {
                                menuPerfilAbierto = false
                            }
                        )

                        DropdownMenuItem(
                            text = { Text("Mis consultas") },
                            leadingIcon = {
                                Icon(Icons.Filled.CalendarMonth, contentDescription = null)
                            },
                            trailingIcon = {
                                Icon(Icons.Filled.ChevronRight, contentDescription = null)
                            },
                            onClick = {
                                menuPerfilAbierto = false
                            }
                        )

                        DropdownMenuItem(
                            text = { Text("Tienda de bienestar") },
                            leadingIcon = {
                                Icon(Icons.Filled.Spa, contentDescription = null)
                            },
                            trailingIcon = {
                                Icon(Icons.Filled.ChevronRight, contentDescription = null)
                            },
                            onClick = {
                                menuPerfilAbierto = false
                            }
                        )

                        HorizontalDivider()

                        DropdownMenuItem(
                            text = { Text("Configuración") },
                            leadingIcon = {
                                Icon(Icons.Filled.Settings, contentDescription = null)
                            },
                            onClick = {
                                menuPerfilAbierto = false
                            }
                        )

                        DropdownMenuItem(
                            text = { Text("Cerrar menú") },
                            leadingIcon = {
                                Icon(Icons.Filled.Close, contentDescription = null)
                            },
                            onClick = {
                                menuPerfilAbierto = false
                            }
                        )
                    }
                }

            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Hola, $nombreUsuario 👋",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = GrafitoHome
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "¿Cómo te gustaría cuidar de ti hoy?",
                fontSize = 16.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(
                    containerColor = CelesteHome
                )
            ) {
                Column(
                    modifier = Modifier.padding(22.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = null,
                        tint = AzulHome,
                        modifier = Modifier.size(32.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Tu bienestar importa",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = GrafitoHome
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Dedica un momento de tu día a conectar contigo y cuidar tu equilibrio.",
                        fontSize = 14.sp,
                        color = GrafitoHome,
                        lineHeight = 21.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Explora SanaEsencia",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = GrafitoHome
            )

            Spacer(modifier = Modifier.height(16.dp))

            FilaAccesoHome(
                icono = {
                    Icon(
                        imageVector = Icons.Filled.CalendarMonth,
                        contentDescription = null,
                        tint = AzulHome,
                        modifier = Modifier.size(28.dp)
                    )
                },
                titulo = "Mis consultas",
                descripcion = "Revisa tus próximas sesiones"
            )

            Spacer(modifier = Modifier.height(12.dp))

            FilaAccesoHome(
                icono = {
                    Icon(
                        imageVector = Icons.Filled.Spa,
                        contentDescription = null,
                        tint = AzulHome,
                        modifier = Modifier.size(28.dp)
                    )
                },
                titulo = "Tienda de bienestar",
                descripcion = "Descubre productos para ti"
            )

            Spacer(modifier = Modifier.height(12.dp))

            FilaAccesoHome(
                icono = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = null,
                        tint = AzulHome,
                        modifier = Modifier.size(28.dp)
                    )
                },
                titulo = "Mi perfil",
                descripcion = "Consulta tus datos personales"
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Un espacio para cuidar de ti",
                modifier = Modifier.fillMaxWidth(),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                fontSize = 13.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
private fun FilaAccesoHome(
    icono: @Composable () -> Unit,
    titulo: String,
    descripcion: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = CelesteHome,
                        shape = RoundedCornerShape(14.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                icono()
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 14.dp)
            ) {
                Text(
                    text = titulo,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = GrafitoHome
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = descripcion,
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
        }
    }
}
