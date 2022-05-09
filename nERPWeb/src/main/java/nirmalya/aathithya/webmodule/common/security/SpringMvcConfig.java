package nirmalya.aathithya.webmodule.common.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class SpringMvcConfig extends WebMvcConfigurerAdapter{

	@Override
	public void addInterceptors (InterceptorRegistry registry) {
		registry.addInterceptor(new RequestInterceptor())
			.excludePathPatterns("/get-state-list","/save-packagess-details","/get-package-amount","/save-signup-details","/assets/**","/extend/**", "/css/**", "/datatables/**", "/FileUpload/**","/download/**", "/js/**", "/login", "/logout", "/register","/" ,"/document/**","/access-denied", "/order-status" ,"/restaurant/kitchen-staff-order-details","/restaurant/kitchen-staff-order-details-modal","/verification-mobile-email","/update-user-password","/error","/lab-dashboard-center-location","/lab-dashboard-center-barchart","/lab/pdf","/lab/center-allocation-report-pdf");;
	}
	
}
