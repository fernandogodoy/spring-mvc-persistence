package br.springannotations.context;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class PersistenceConfig {

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setPackagesToScan("br.springannotations.model");
		emf.setJpaVendorAdapter(jpaVendorApapter());
		emf.setJpaProperties(jpaProperties());
		return emf;
	}

	@Bean
	public JpaVendorAdapter jpaVendorApapter() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		return jpaVendorAdapter;
	}
	
	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManager){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManager);
		return transactionManager;
	}

	private Properties jpaProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "create-drop");
		return properties;
	}
}
