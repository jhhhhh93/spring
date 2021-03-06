package kr.or.ddit.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

@Configuration
//<context:property-placeholder location="classpath:kr/or/ddit/config/mybatis/db.properties"/>
@PropertySource(value = "classpath:kr/or/ddit/config/mybatis/db.properties")
@ImportResource("classpath:kr/or/ddit/config/spring/context-transaction.xml")
public class DatasourceConfig {
	
	@Autowired
	private Environment env;
	
//	<bean name="datasource" class="org.apache.commons.dbcp2.BasicDataSource">
//		<property name="driverClassName" value="${jdbc.driver}"></property>
//		<property name="url" value="${jdbc.url}"></property>
//		<property name="username" value="${jdbc.user}"></property>
//		<property name="password" value="${jdbc.pass}"></property>
//	</bean>
	@Bean
	public DataSource datasource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.pass"));
		return dataSource;
	}
	
//	<bean name="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
//		<property name="dataSource" ref="datasource"/>
//		<property name="configLocation" value="classpath:kr/or/ddit/config/mybatis/mybatis-config.xml"/>
//	</bean>
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(datasource());
		sqlSessionFactory.setConfigLocation(new ClassPathResource("kr/or/ddit/config/mybatis/mybatis-config.xml"));
		return sqlSessionFactory;
	}
	
//	<bean name="sqlSessionTemplate" class="org.mybatis.spring.SqlSessionTemplate">
//		<constructor-arg ref="sqlSessionFactory"/>
//	</bean>
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() {
		SqlSessionFactory factory = null;
		
		try {
			factory = sqlSessionFactory().getObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(factory);
		return sqlSessionTemplate;
	}

}
