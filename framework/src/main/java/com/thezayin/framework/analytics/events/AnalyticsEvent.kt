package com.thezayin.framework.analytics.events

import android.os.Bundle
import com.thezayin.framework.analytics.utils.AnalyticsConstant

/**
 * A sealed class representing different types of analytics events within the app.
 *
 * This class allows us to track a variety of events related to user actions and system events.
 * The events include settings interactions, ad impressions, screen views, and custom events.
 *
 * Each event type carries its unique name and relevant parameters (in a [Bundle]) that can be sent to an analytics service like Firebase.
 */
sealed class AnalyticsEvent(
    val event: String,  // Event name
    val args: Bundle?   // Event parameters
) {
    /**
     * Represents the event when the user views a screen.
     *
     * @param screenName Name of the screen viewed.
     */
    class ScreenViewEvent(
        screenName: String
    ) : AnalyticsEvent(
        AnalyticsConstant.SCREEN_VIEW,
        Bundle().apply {
            putString("screen_name", screenName)
        }
    )

    /**
     * Represents the event when the user shares an image.
     *
     * @param status The action taken (e.g., "UserSharedImage").
     */
    class ShareImageEvent(
        status: String
    ) : AnalyticsEvent(
        "share_image",
        Bundle().apply {
            putString("status", status)
        }
    )

    class LikeEvent(
        status: String
    ) : AnalyticsEvent(
        "LikeEvent",
        Bundle().apply {
            putString("status", status)
        }
    )

    class DownloadEvent(
        status: String
    ) : AnalyticsEvent(
        "DownloadEvent",
        Bundle().apply {
            putString("status", status)
        }
    )



    class ImageClickEvent(
        status: String,
        imageId: String
    ) : AnalyticsEvent(
        "ImageClickEvent",
        Bundle().apply {
            putString("status", status)
            putString("imageId", imageId)
        }
    )

    class CategoryClickEvent(
        status: String,
        categoryId: String
    ) : AnalyticsEvent(
        "CategoryClickEvent",
        Bundle().apply {
            putString("status", status)
            putString("categoryId", categoryId)
        }
    )

    class LikeHomeEvent(
        status: String
    ) : AnalyticsEvent(
        "LikeEvent",
        Bundle().apply {
            putString("status", status)
        }
    )

    // Track click on an item in the Favorites screen
    class FavoriteItemClickEvent(
        status: String,
        imageId: String
    ) : AnalyticsEvent(
        "FavoriteItemClickEvent",
        Bundle().apply {
            putString("status", status)
            putString("imageId", imageId)
        }
    )


    class AdRewardEvent(
        event: String,
        reward: String
    ) : AnalyticsEvent(
        event,
        Bundle().apply {
            putString("reward", reward)
        }
    )

    class SettingClickEvent(
        status: String
    ) : AnalyticsEvent(
        "SettingClickEvent",
        Bundle().apply {
            putString("status", status)
        }
    )

    class LikeAdImpressionEvent(
        status: String
    ) : AnalyticsEvent(
        "LikeAdImpressionEvent",
        Bundle().apply {
            putString("status", status)
        }
    )



    /**
     * Represents the event when a user clicks on the Privacy Policy link.
     */
    class SettingsPrivacyPolicy(
        status: String
    ) : AnalyticsEvent(
        AnalyticsConstant.SETTINGS_PRIVACY_POLICY,
        Bundle().apply {
            putString("status", status)  // status could indicate if the link was clicked or something else
        }
    )

    /**
     * Represents the event when a user clicks on the Terms & Conditions link.
     */
    class SettingsTermsConditions(
        status: String
    ) : AnalyticsEvent(
        AnalyticsConstant.SETTINGS_TERMS_CONDITION,
        Bundle().apply {
            putString("status", status)
        }
    )

    /**
     * Represents the event when a user clicks on the 'Contact Us' link.
     */
    class SettingsContactUs(
        status: String
    ) : AnalyticsEvent(
        AnalyticsConstant.SETTINGS_CONTACT_US,
        Bundle().apply {
            putString("status", status)
        }
    )

    /**
     * Represents the event when a user clicks on the "Rate Us" option in settings.
     */
    class SettingsRateUs(
        status: String
    ) : AnalyticsEvent(
        AnalyticsConstant.SETTINGS_RATE_US,
        Bundle().apply {
            putString("status", status)
        }
    )

    /**
     * Represents the event when a user clicks on the "Leave a Rating & Review" option.
     */
    class LeaveRatingReview(
        status: String
    ) : AnalyticsEvent(
        AnalyticsConstant.SETTINGS_MORE_APPS,
        Bundle().apply {
            putString("status", status)
        }
    )

    /**
     * Represents the event when an ad impression occurs.
     * This is useful for tracking ad performance and user engagement.
     *
     * @param adProvider Name of the ad provider (e.g., AdMob).
     * @param adType Type of the ad (e.g., Interstitial, Banner).
     */
    class AdImpressionEvent(
        adProvider: String,
        adType: String
    ) : AnalyticsEvent(
        AnalyticsConstant.AD_REVENUE,
        Bundle().apply {
            putString("ad_provider", adProvider)
            putString("ad_type", adType)
        }
    )

    /**
     * Represents the event when a user clicks on a banner ad.
     */
    class ShowBannerAdEvent(
        status: String
    ) : AnalyticsEvent(
        AnalyticsConstant.SCREEN_VIEW,
        Bundle().apply {
            putString("status", status)
        }
    )

    /**
     * Represents the event when a user interacts with a button.
     *
     * @param buttonName Name of the button clicked.
     */
    class ButtonClickEvent(
        buttonName: String
    ) : AnalyticsEvent(
        "button_click",
        Bundle().apply {
            putString("button_name", buttonName)
        }
    )

    /**
     * Represents an event for failed ad initialization.
     *
     * @param reason The reason why the ad initialization failed.
     */
    class AdInitializationFailed(
        reason: String
    ) : AnalyticsEvent(
        "ad_initialization_failed",
        Bundle().apply {
            putString("reason", reason)
        }
    )
}
