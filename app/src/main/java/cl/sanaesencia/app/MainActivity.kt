package cl.sanaesencia.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.sanaesencia.app.ui.theme.SanaEsenciaTheme

private val AzulSana = Color(0xFF7899BC)
private val AzulClaro = Color(0xFFEAF2F9)
private val Grafito = Color (0xFF202020)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SanaEsenciaTheme {
                    PantallaBienvenida()
            }
        }
    }
}

@Composable
fun PantallaBienvenida(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 28.dp, vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "SanaEsencia",
                fontSize = 34.sp,
                fontWeight = FontWeight.Light,
                color = Grafito
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "BIENESTAR INTEGRAL",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
                color = AzulSana
            )

            Text(
                text = "Tu bienestar,\n en un solo lugar",
                fontSize = 30.sp,
                lineHeight = 38.sp,
                fontWeight = FontWeight.SemiBold,
                color = Grafito,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Encuentra tus terapias, organiza tus horas " +
                        "y sigue acompañando tu bienestar.",
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(36.dp))

            Button(
                onClick = {
                    // Acá se avanzará al registro
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AzulSana
                )
            ) {
                Text(
                    text = "Crear mi cuenta",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = {
                        // Este es para ir al inicio de sesión
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text(
                        text = "Ya tengo una cuenta",
                        color = AzulSana,
                        fontSize = 16.sp
                    )
                }
                    Spacer(modifier = Modifier.height(28.dp))

                    Text(
                        text = "Un espacio para cuidar de ti",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
            }
        }
    }


