package laz2.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author Dmitry Lazurkin
 */
public abstract class BaseTest {

	@Autowired
	protected ApplicationContext applicationContext;

	@PersistenceContext
	protected EntityManager em;

	@Autowired
	protected TransactionTemplate tt;

	protected static void run(BaseTest test) {
		System.setProperty("hibernate.package", test.getClass().getPackage().getName());
		
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		context.registerShutdownHook();

		AutowireCapableBeanFactory beanFactory = context.getAutowireCapableBeanFactory();
		beanFactory.autowireBeanProperties(test, AutowireCapableBeanFactory.AUTOWIRE_NO, true);

		try {
			test.run();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	protected abstract void run();
	
	protected void println(Object o) {
		System.out.println(o);
	}
}
