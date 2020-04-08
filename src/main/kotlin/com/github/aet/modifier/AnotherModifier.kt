package com.github.aet.modifier

import com.cognifide.aet.communication.api.metadata.CollectorStepResult
import com.cognifide.aet.job.api.collector.CollectorJob
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class AnotherModifier(private val webDriver: WebDriver, private val defaultColor: String) : CollectorJob {

    private var color = defaultColor

    override fun collect(): CollectorStepResult {
        var result: CollectorStepResult
        try {
            LOGGER.trace("Example modifier will apply {} bg colour", color)
            val command = String.format(EXAMPLE_CHANGE_BACKGROUND_MODIFICATION, color)
            if (webDriver is JavascriptExecutor) {
                webDriver.executeScript(command)
            } else {
                throw RuntimeException("Provided webdriver is not a JavascriptExecutor")
            }
            result = CollectorStepResult.newModifierResult()
        } catch (e: Exception) {
            val message = String.format("Can't execute JavaScript command. Error: %s ", e.message)
            result = CollectorStepResult.newProcessingErrorResult(message)
            LOGGER.warn(message, e)
        }
        return result
    }

    override fun setParameters(params: MutableMap<String, String>) {
        color = params.getOrDefault(COLOR_PARAM, defaultColor)
    }

    companion object Options {
        val LOGGER: Logger = LoggerFactory.getLogger(AnotherModifier::class.java)
        const val COLOR_PARAM = "color"
        const val EXAMPLE_CHANGE_BACKGROUND_MODIFICATION = "document.body.style.background = '%s';"
    }
}