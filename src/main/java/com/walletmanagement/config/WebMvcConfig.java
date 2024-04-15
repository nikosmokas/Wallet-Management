// package com.walletmanagement.config;


// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// import org.thymeleaf.spring6.SpringTemplateEngine;
// import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
// import org.thymeleaf.spring6.view.ThymeleafViewResolver;

// @Configuration
// public class WebMvcConfig implements WebMvcConfigurer {

//     @Override
//     public void configureViewResolvers(@SuppressWarnings("null") ViewResolverRegistry registry) {
//         // Create SpringResourceTemplateResolver
//         SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
//         templateResolver.setPrefix("/WEB-INF/views/");
//         templateResolver.setSuffix(".html");
//         templateResolver.setTemplateMode("HTML");
//         templateResolver.setCharacterEncoding("UTF-8");
//         templateResolver.setCacheable(false); // Disable cache for development
        
//         // Create SpringTemplateEngine
//         SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//         templateEngine.setTemplateResolver(templateResolver);
        
//         // Register ThymeleafViewResolver
//         ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//         viewResolver.setTemplateEngine(templateEngine);
        
//         // Add ThymeleafViewResolver to the registry
//         registry.viewResolver(viewResolver);
//     }
// }
