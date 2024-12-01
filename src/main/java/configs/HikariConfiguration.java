package configs;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HikariConfiguration {
    @Bean("hikariDataSource")
    public DSLContext hikariDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/cats_ww_sloniki");
        config.setUsername("postgres");
        config.setPassword("postgres");
        return new DefaultDSLContext(new HikariDataSource(config),SQLDialect.POSTGRES);
    }
}
