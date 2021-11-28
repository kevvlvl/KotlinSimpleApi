import io.cucumber.junit.platform.engine.Constants
import org.junit.platform.suite.api.Suite
import org.junit.platform.suite.api.IncludeEngines
import org.junit.platform.suite.api.SelectClasspathResource
import org.junit.platform.suite.api.ConfigurationParameter


@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com/kevvlvl/simpleapi/handler")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty")
class CucumberTest