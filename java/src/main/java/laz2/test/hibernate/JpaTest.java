package laz2.test.hibernate;

import laz2.test.BaseTest;

import org.hibernate.Session;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

/**
 * @author Dmitry Lazurkin
 */
public class JpaTest extends BaseTest {

	public static void main(String[] args) {
		run(new JpaTest());
	}

	@SuppressWarnings("unused")
	private Session session() {
		return em.unwrap(Session.class);
	}
	
	@Override
	protected void run() {
		tt.execute(new TransactionCallbackWithoutResult() {

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				

				for (int k = 0; k < 10; ++k) {
					Parent p = new Parent();
					p.data = "parent_" + k;
					em.persist(p);
					
					for (int i = 0; i < 10; ++i) {
						Child c = new Child();
						c.parent = p;
						c.data = "child_" + k + "_" + i;
						em.persist(c);

						for (int j = 0; j < 10; ++j) {
							GrandChild gc = new GrandChild();
							gc.parent = c;
							gc.data = "grandchild_" + k + "_" + i + "_" + j;
							em.persist(gc);
							
							for (int l = 0; l < 10; ++l) {
								GrandGrandChild ggc = new GrandGrandChild();
								ggc.parent = gc;
								ggc.data = "grandgrandchild_" + k + "_" + i + "_" + j + "_" + l;
								em.persist(ggc);
							}
						}
					}
				}
			}
		});

		System.out.println("\n\n\nEnd data generating\n\n\n");

		//		Parent p = em.createQuery("from Parent p join fetch p.childs where p.id = :id", Parent.class).setParameter("id", parentId).getSingleResult();
		//		System.out.println(p.getChilds());

		//		Child c = em.createQuery("from Child c join fetch c.parent p join fetch p.childs cs join fetch cs.parent where c.id = :id", Child.class).setParameter("id", parentId).getSingleResult();
		//		System.out.println(c);
		//		System.out.println(c.getParent());
		//		System.out.println(c.getParent().getChilds());
		//		System.out.println(c.getParent().getChilds().get(0).getParent());
		//		System.out.println(c.getParent().getChilds().get(1).getParent());

		//		Child c = em.createQuery("from Child c where c.id = :id", Child.class).setParameter("id", parentId).getSingleResult();
		//		System.out.println(c.getParent());

		//		Child c = em.createQuery("from Child c join fetch c.parent.childs where c.id = :id", Child.class).setParameter("id", parentId).getSingleResult();
		//		System.out.println(c);
		//		System.out.println(c.getParent());
		//		System.out.println(c.getParent().getChilds());
		//		System.out.println(c.getParent().getChilds().get(0).getParent());
		//		System.out.println(c.getParent().getChilds().get(1).getParent());

		//		CriteriaBuilder cb = em.getCriteriaBuilder();
		//		CriteriaQuery<Child> query = cb.createQuery(Child.class);
		//		Root<Child> root = query.from(Child.class);
		//
		//		query.select(root).where(root.<Long> get("id").in(1));
		//		Fetch<Object, Object> fetch = root.fetch("parent");
		//
		//		Child c = em.createQuery(query).getSingleResult();
		//		System.out.println(c);
		//		System.out.println(c.getParent());

		//		System.out.println(em.find(Parent.class, 1L));

//		System.out.println(em.find(Parent.class, 1L));
		tt.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
//				session().enableFetchProfile("Child-join-parent");
//				session().enableFetchProfile("GrandChild-join-parent");
//				session().enableFetchProfile("GrandGrandChild-join-parent");
				GrandGrandChild p = em.find(GrandGrandChild.class, 1L);
//				System.out.println(p);
//				System.out.println(p.parent);
//				System.out.println(p.parent.parent);
//				System.out.println(p.parent.parent.parent);
//				session().disableFetchProfile("Child-join-parent");
//				session().disableFetchProfile("GrandChild-join-parent");
//				session().disableFetchProfile("GrandGrandChild-join-parent");
			}
		});
	}
}
