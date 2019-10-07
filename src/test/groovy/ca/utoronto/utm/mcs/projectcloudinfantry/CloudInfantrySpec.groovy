package ca.utoronto.utm.mcs.projectcloudinfantry

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.context.annotation.PropertySource
import spock.lang.Stepwise

/**
 * EXAMPLE TEST CLASS
 */
@Stepwise
@AutoConfigureMockMvc
@PropertySource(value = "classpath:application-test.yml")
class CloudInfantrySpec extends BaseSpecification {

    def 'Example test'() {
        // this better pass
    }

}
