package laz2.test.hibernate;

import org.hibernate.Session;

import laz2.test.BaseTest;

/**
 * @author Dmitry Lazurkin
 */
public abstract class BaseHibernateTest extends BaseTest {

	protected Session session() {
		return em.unwrap(Session.class);
	}
}
