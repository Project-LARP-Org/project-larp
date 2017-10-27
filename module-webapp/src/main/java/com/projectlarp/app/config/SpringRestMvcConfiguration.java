package com.projectlarp.app.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@Configuration
@ConditionalOnClass(DataSource.class)
public class SpringRestMvcConfiguration extends RepositoryRestMvcConfiguration {

  @Override
  public RepositoryRestConfiguration config() {
    RepositoryRestConfiguration config = super.config();
    config.setBasePath("/api/");
    return config;
  }
  /*
  @Override
  protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
      config.exposeIdsFor( //
//    		  Exchange.class, //
//    		  ServiceFlow.class //
    		  );
  }
  */
}