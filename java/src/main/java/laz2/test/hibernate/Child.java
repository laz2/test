package laz2.test.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
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
@Table(name = "jpa_child")
@FetchProfiles({
		@FetchProfile(name = "Child-join-parent", fetchOverrides = { @FetchOverride(entity = Child.class, association = "parent", mode = FetchMode.JOIN) }),
		@FetchProfile(name = "Child-join-parent-join-childs", fetchOverrides = {
				@FetchOverride(entity = Child.class, association = "parent", mode = FetchMode.JOIN),
				@FetchOverride(entity = Child.class, association = "childs", mode = FetchMode.JOIN) }) })
@Cacheable
@EntityListeners(value = { EntityListener.class })
public class Child extends BaseEntity {

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	public Parent parent;

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@BatchSize(size = 100)
	@OrderBy("data")
	public List<GrandChild> childs = new ArrayList<GrandChild>();
}
