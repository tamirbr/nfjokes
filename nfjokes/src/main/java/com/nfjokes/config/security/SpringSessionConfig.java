package com.nfjokes.config.security;

import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@EnableJdbcHttpSession(maxInactiveIntervalInSeconds = 1200)
public class SpringSessionConfig {

}
