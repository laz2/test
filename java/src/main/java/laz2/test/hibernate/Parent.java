package laz2.test.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.annotations.FetchProfile.FetchOverride;
import org.hibernate.annotations.FetchProfiles;

/**
 * @author Dmitry Lazurkin
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "jpa_parent")
@FetchProfiles({ @FetchProfile(name = "Parent-join-childs", fetchOverrides = { @FetchOverride(
		entity = Parent.class,
		association = "childs",
		mode = FetchMode.JOIN) }) })
@EntityListeners(value = { EntityListener.class })
public class Parent extends BaseEntity {

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@BatchSize(size = 16)
	@OrderBy("data")
	public List<Child> childs = new ArrayList<Child>();
}
