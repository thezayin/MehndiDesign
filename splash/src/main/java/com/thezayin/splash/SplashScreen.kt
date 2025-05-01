import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.thezayin.framework.analytics.events.AnalyticsEvent
import com.thezayin.splash.SplashViewModel
import com.thezayin.splash.component.SplashScreenContent
import kotlinx.coroutines.delay
import org.koin.compose.koinInject

@Composable
fun SplashScreen(
    navigateToLanguageScreen: () -> Unit = {}, navigateToHome: () -> Unit
) {
    val viewModel: SplashViewModel = koinInject()
    val activity = LocalActivity.current as Activity
    val adManager = viewModel.admobManager
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        adManager.loadAd(activity)
    }

    viewModel.analytics.logEvent(AnalyticsEvent.ScreenViewEvent("SplashScreen"))

    LaunchedEffect(Unit) {
        delay(10000)
        adManager.showAd(activity = activity, showAd = true, adImpression = {}, onNext = {
            if (viewModel.isFirstTime) {
                navigateToHome()
            } else {
                navigateToHome()
            }
        })
    }

    SplashScreenContent(
        text = state.currentSplashText
    )
}
