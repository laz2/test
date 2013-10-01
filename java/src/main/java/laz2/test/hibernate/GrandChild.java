package laz2.test.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.annotations.FetchProfiles;
import org.hibernate.annotations.FetchProfile.FetchOverride;

/**
 * @author Dmitry Lazurkin
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "jpa_grand_child")
@FetchProfiles({ @FetchProfile(name = "GrandChild-join-parent", fetchOverrides = { @FetchOverride(
		entity = GrandChild.class,
		association = "parent",
		mode = FetchMode.JOIN) }) })
@EntityListeners(value = { EntityListener.class })
public class GrandChild extends BaseEntity {

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	public Child parent;

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@BatchSize(size = 1000)
	@OrderBy("data")
	public List<GrandGrandChild> childs = new ArrayList<GrandGrandChild>();
}
