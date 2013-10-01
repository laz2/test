package laz2.test.hibernate.fetchprofile.manytoone;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import laz2.test.hibernate.BaseEntity;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.annotations.FetchProfile.FetchOverride;
import org.hibernate.annotations.FetchProfiles;


/**
 * @author Dmitry Lazurkin
 */
@Entity
@FetchProfiles({ @FetchProfile(name = "hibernate.fetchprofile.manytoone.Node5 join parent", fetchOverrides = { @FetchOverride(
		entity = Node5.class,
		association = "parent",
		mode = FetchMode.JOIN) }) })
@Table(name = "hibernate_fetchprofile_manytoone_Node5")
public class Node5 extends BaseEntity {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	public Node4 parent;
}
