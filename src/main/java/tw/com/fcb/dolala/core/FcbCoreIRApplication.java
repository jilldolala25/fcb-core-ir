package tw.com.fcb.dolala.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "tw.com.fcb.dolala.core.common")
public class FcbCoreIRApplication {

	public static void main(String[] args) {
		SpringApplication.run(FcbCoreIRApplication.class, args);
	}

}
