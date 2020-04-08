package com.github.aet.modifier

import com.cognifide.aet.job.api.collector.CollectorFactory
import com.cognifide.aet.job.api.collector.CollectorJob
import com.cognifide.aet.job.api.collector.CollectorProperties
import com.cognifide.aet.job.api.collector.WebCommunicationWrapper
import org.osgi.service.component.annotations.Activate
import org.osgi.service.component.annotations.Component
import org.osgi.service.metatype.annotations.AttributeDefinition
import org.osgi.service.metatype.annotations.AttributeType
import org.osgi.service.metatype.annotations.Designate
import org.osgi.service.metatype.annotations.ObjectClassDefinition

@Component
@Designate(ocd = AnotherModifierFactory.Config::class)
class AnotherModifierFactory : CollectorFactory {

    @ObjectClassDefinition(
            name = "AET Another Modifier Factory",
            description = "AET Another Modifier Factory Config")
    annotation class Config(
            @get:AttributeDefinition(
                    name = "Default background color",
                    description = "This colour will be applied to the background color if none is provided in the test configuration.",
                    type = AttributeType.STRING
            ) val defaultColor: String = "red"
    )

    private lateinit var config: Config

    @Activate
    fun activate(config: Config) {
        this.config = config
    }

    override fun getName() = "another"

    override fun createInstance(properties: CollectorProperties, parameters: MutableMap<String, String>, webCommunicationWrapper: WebCommunicationWrapper): CollectorJob {
        val modifier = ExampleModifier(webCommunicationWrapper.webDriver, config.defaultColor)
        modifier.setParameters(parameters)
        return modifier
    }
}