package jacs.apps.powermega.Views
import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContentProviderCompat.requireContext
import jacs.apps.powermega.ui.theme.PowerMegaTheme
import jacs.apps.powermega.R


sealed class DrawerScreens(val title: Int, val route: String, val icon: ImageVector) {

    object MyTickets : DrawerScreens(R.string.my_tickets, "mytickets",Icons.Filled.MenuBook)
    object PastResults : DrawerScreens(R.string.past_results, "pastresults",Icons.Filled.Image)
    object Simulator : DrawerScreens( R.string.simulator, "simulator",Icons.Filled.Camera)
}

private val screens = listOf(
    DrawerScreens.MyTickets,
    DrawerScreens.PastResults,
    DrawerScreens.Simulator
)

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: String) -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Column(modifier.background(color = MaterialTheme.colors.primary).fillMaxWidth(1f), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = modifier.size(100.dp,100.dp),
                painter = painterResource(R.drawable.powerstar1),
                contentDescription = "App icon"
            )
            Text(
                text = context.getString(R.string.lottery_tools),
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                text = context.getString(R.string.header_text),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onPrimary
            )
        }

        Divider(color = MaterialTheme.colors.onPrimary)
        screens.forEach { screen ->

            Spacer(Modifier.height(24.dp))
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
                Icon(screen.icon, contentDescription = context.getString(screen.title),tint = MaterialTheme.colors.onPrimary)
                Spacer(Modifier.width(20.dp))
                Text(
                    text = context.getString(screen.title),
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.clickable {
                        onDestinationClicked(screen.route)
                    }
                )
            }
            Spacer(Modifier.height(24.dp))
            Divider(color = MaterialTheme.colors.onPrimary)

        }
    }
}

@Preview
@Composable
fun DrawerPreview() {
    PowerMegaTheme() {
        Drawer{}
    }
}