package cn.ymsys.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

@ComponentScan("cn.ymsys.api.*")
@SpringBootApplication
@EnableAsync
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);

		ActiveRecordPlugin arp = new ActiveRecordPlugin(_JFinalDemoGenerator.getDataSource());
		arp.start();
	}

}
