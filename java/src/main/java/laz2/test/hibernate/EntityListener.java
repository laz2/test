package laz2.test.hibernate;

import javax.persistence.PreRemove;

/**
 * @author Dmitry Lazurkin
 */
public class EntityListener {

	@PreRemove
	public void preRemove(Object o) {
	}
	
}
