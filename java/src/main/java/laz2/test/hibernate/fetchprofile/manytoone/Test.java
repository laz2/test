package laz2.test.hibernate.fetchprofile.manytoone;

import laz2.test.hibernate.BaseHibernateTest;

import org.hibernate.Hibernate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

/**
 * @author Dmitry Lazurkin
 */
public class Test extends BaseHibernateTest {

	public static void main(String[] args) {
		run(new Test());
	}

	@Override
	protected void run() {
		tt.execute(new TransactionCallbackWithoutResult() {

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				for (int k = 0; k < 2; ++k) {
					Node1 p = new Node1();
					p.data = p.getClass().getSimpleName() + "_" + k;
					em.persist(p);
					
					for (int i = 0; i < 2; ++i) {
						Node2 c = new Node2();
						c.parent = p;
						c.data = c.getClass().getSimpleName() + "_" + k + "_" + i;
						em.persist(c);

						for (int j = 0; j < 2; ++j) {
							Node3 gc = new Node3();
							gc.parent = c;
							gc.data = c.getClass().getSimpleName() + "_" + k + "_" + i + "_" + j;
							em.persist(gc);
							
							for (int l = 0; l < 2; ++l) {
								Node4 ggc = new Node4();
								ggc.parent = gc;
								ggc.data = c.getClass().getSimpleName() + "_" + k + "_" + i + "_" + j + "_" + l;
								em.persist(ggc);
								
								for (int z = 0; z < 2; ++z) {
									Node5 n5 = new Node5();
									n5.parent = ggc;
									n5.data = n5.getClass().getSimpleName() + "_" + k + "_" + i + "_" + j + "_" + l + "_" + z;
									em.persist(n5);
								}
							}
						}
					}
				}
			}
		});

		System.out.println("\n\n\nEnd data generating\n\n\n");

		tt.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				session().enableFetchProfile("hibernate.fetchprofile.manytoone.Node2 join parent");
				session().enableFetchProfile("hibernate.fetchprofile.manytoone.Node3 join parent");
				session().enableFetchProfile("hibernate.fetchprofile.manytoone.Node4 join parent");
				session().enableFetchProfile("hibernate.fetchprofile.manytoone.Node5 join parent");
				Node5 n = em.find(Node5.class, 1L);
				println(n);
				Hibernate.initialize(n.parent);
				println(n.parent);
				Hibernate.initialize(n.parent.parent);
				println(n.parent.parent);
				Hibernate.initialize(n.parent.parent.parent);
				println(n.parent.parent.parent);
				Hibernate.initialize(n.parent.parent.parent.parent);
				println(n.parent.parent.parent.parent);
			}
		});
	}
}
