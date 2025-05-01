package com.thezayin.framework.analytics.analytics

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics
import com.thezayin.framework.analytics.events.AnalyticsEvent
import timber.log.Timber

/**
 * Implementation of the [Analytics] interface that logs events to a Firebase backend.
 *
 * This class is responsible for interacting with Firebase Analytics to log various types of events
 * related to user interactions or system events. It takes in an [AnalyticsEvent] and sends it to
 * Firebase Analytics for tracking.
 *
 * @param analytics The FirebaseAnalytics instance used to log events to Firebase.
 */
class AnalyticsImpl(
    private val analytics: FirebaseAnalytics,
) : Analytics {

    /**
     * Logs an event to Firebase Analytics.
     *
     * This method accepts an [AnalyticsEvent], which contains the event name and associated
     * parameters (arguments). It logs this information to Firebase Analytics for tracking.
     *
     * @param event The [AnalyticsEvent] to be logged. It contains the event name and optional arguments.
     */
    @SuppressLint("BinaryOperationInTimber") // Suppressing lint warning for binary operation in Timber logs
    override fun logEvent(event: AnalyticsEvent) {

        // Log the event and its arguments using Timber
        Timber.tag("Analytics")
            .d("FirebaseAnalyticsRepository eventName....${event.event} arguments... ${event.args} ")

        // If the event name is available, log the event to Firebase Analytics
        event.event?.let { eventName ->
            analytics.logEvent(eventName, event.args) // Log event with arguments to Firebase Analytics
        }
    }
}
