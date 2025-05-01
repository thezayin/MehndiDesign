package com.thezayin.framework.analytics.analytics

import com.thezayin.framework.analytics.events.AnalyticsEvent

/**
 * Interface for logging analytics events.
 *
 * This interface serves as the contract for logging events related to user actions or system events.
 * Any analytics provider (e.g., Firebase Analytics, Mixpanel, etc.) can implement this interface
 * to log events in the application, allowing for easy integration with any analytics platform.
 *
 * The methods in this interface allow for logging different types of events and passing relevant
 * data associated with the event.
 */
interface Analytics {

    /**
     * Logs an analytics event with the given [AnalyticsEvent].
     *
     * This method allows any class that implements this interface to log a specific event.
     * The event contains all the data necessary for logging the action or event being tracked.
     *
     * @param event The [AnalyticsEvent] to be logged. This contains the event name and associated parameters.
     */
    fun logEvent(event: AnalyticsEvent)
}
