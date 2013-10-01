package laz2.test.hibernate.fetchprofile.manytoone;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.annotations.FetchProfiles;
import org.hibernate.annotations.FetchProfile.FetchOverride;

import laz2.test.hibernate.BaseEntity;


/**
 * @author Dmitry Lazurkin
 */
@Entity
@FetchProfiles({ @FetchProfile(name = "hibernate.fetchprofile.manytoone.Node4 join parent", fetchOverrides = { @FetchOverride(
		entity = Node4.class,
		association = "parent",
		mode = FetchMode.JOIN) }) })
@Table(name = "hibernate_fetchprofile_manytoone_Node4")
public class Node4 extends BaseEntity {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	public Node3 parent;
	
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	@OrderBy("data")
	public List<Node5> childs = new ArrayList<Node5>();
}
