package com.example.autores.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Value("${app.upload.dir:/app/uploads/images/}")
  private String uploadDir;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // Servir archivos estáticos desde el volumen persistente
    registry.addResourceHandler("/uploads/images/**")
        .addResourceLocations("file:" + uploadDir);

    // Mantener la configuración por defecto para otros archivos estáticos
    registry.addResourceHandler("/images/**")
        .addResourceLocations("classpath:/static/images/");
  }
}
