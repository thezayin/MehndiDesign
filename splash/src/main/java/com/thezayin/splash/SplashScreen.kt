import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.thezayin.framework.extension.ads.showAppOpenAd
import com.thezayin.framework.extension.functions.checkForInternet
import com.thezayin.splash.SplashViewModel
import com.thezayin.splash.component.SplashScreenContent
import kotlinx.coroutines.delay
import org.koin.compose.koinInject

/**
 * Composable function for displaying the splash screen.
 * It checks the network connection, shows an ad if connected, or handles navigation otherwise.
 *
 * @param onNavigate A callback function to navigate to the next screen.
 */
@Composable
fun SplashScreen(onNavigate: () -> Unit) {
    // ViewModel injection using Koin
    val viewModel: SplashViewModel = koinInject()

    // Access the current context and activity
    val context = LocalContext.current
    val activity = LocalContext.current as Activity

    // State to track network connection status
    val checkNetwork = remember { mutableStateOf(false) }

    // Launch side-effect for network check and ad handling
    LaunchedEffect(Unit) {
        if (!context.checkForInternet()) {
            // If no internet, wait for 5 seconds and set network status to true
            delay(5000L)
            checkNetwork.value = true
        } else {
            // If internet is available, show the app open ad after 3 seconds
            delay(6000L)
            showAppOpenAd(
                activity,
                viewModel.googleManager,
                viewModel.remoteConfig.adConfigs.adOnSplashScreen
            ) {
                // Navigate to the next screen after the ad
                onNavigate()
            }
        }
    }

    // Display the splash screen content
    SplashScreenContent(
        checkNetwork = checkNetwork
    )
}
