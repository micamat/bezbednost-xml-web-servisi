package project;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	public ServletContextInitializer initializer() {
	    return new ServletContextInitializer() {
			
	        @Override
	        public void onStartup(ServletContext servletContext) throws ServletException {
	            servletContext.setInitParameter("springJspExpressionSupport", "false");
	        }
	    };
	}
	
}
