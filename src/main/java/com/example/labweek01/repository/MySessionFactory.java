package com.example.labweek01.repository;


import com.example.labweek01.models.Account;
import com.example.labweek01.models.Grant;
import com.example.labweek01.models.Log;
import com.example.labweek01.models.Role;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class MySessionFactory {
	private static final SessionFactory sessionFactory;


	static {
		try {
			Configuration configuration = new Configuration();

			// Cấu hình kết nối cơ sở dữ liệu
			configuration.setProperty("hibernate.connection.driver_class", "org.mariadb.jdbc.Driver");
			configuration.setProperty("hibernate.connection.url", "jdbc:mariadb://localhost:3306/week01");
			configuration.setProperty("hibernate.connection.username", "root");
			configuration.setProperty("hibernate.connection.password", "12345678");

			// Cấu hình dialect cho MariaDB
			configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MariaDBDialect");

			// Tùy chọn cho việc tạo bảng tự động (update)
			configuration.setProperty("hibernate.hbm2ddl.auto", "update");

			// Đăng ký các annotated class
			configuration.addAnnotatedClass(Log.class);
			configuration.addAnnotatedClass(Account.class);
			configuration.addAnnotatedClass(Role.class);
			configuration.addAnnotatedClass(Grant.class);

			sessionFactory = configuration.buildSessionFactory();
		} catch (Throwable ex) {
			// Xử lý exception
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
