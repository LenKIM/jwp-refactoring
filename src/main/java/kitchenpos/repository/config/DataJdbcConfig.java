package kitchenpos.repository.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

import java.util.Arrays;

@Configuration
public class DataJdbcConfig extends AbstractJdbcConfiguration {

    @Bean
    public JdbcCustomConversions jdbcCustomConversions() {
        return new JdbcCustomConversions(Arrays.asList(
                Converters.NameToString.INSTANCE, Converters.StringToName.INSTANCE,
                Converters.DecimalToPrice.INSTANCE, Converters.PriceToDecimal.INSTANCE
        ));
    }
}
