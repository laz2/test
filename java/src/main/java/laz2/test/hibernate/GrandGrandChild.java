package laz2.test.hibernate;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.annotations.FetchProfiles;
import org.hibernate.annotations.FetchProfile.FetchOverride;

/**
 * @author Dmitry Lazurkin
 */
@SuppressWarnings("serial")
@Entity
@Cacheable
@FetchProfiles({ @FetchProfile(name = "GrandGrandChild-join-parent", fetchOverrides = { @FetchOverride(
		entity = GrandGrandChild.class,
		association = "parent",
		mode = FetchMode.JOIN) }) })
@Table(name = "jpa_grand_grand_child")
@EntityListeners(value = { EntityListener.class })
public class GrandGrandChild extends BaseEntity {

	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	public GrandChild parent;
}
