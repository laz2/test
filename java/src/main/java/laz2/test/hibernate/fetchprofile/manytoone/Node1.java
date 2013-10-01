package laz2.test.hibernate.fetchprofile.manytoone;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import laz2.test.hibernate.BaseEntity;


/**
 * @author Dmitry Lazurkin
 */
@Entity
@Table(name = "hibernate_fetchprofile_manytoone_Node1")
public class Node1 extends BaseEntity {

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	@OrderBy("data")
	public List<Node2> childs = new ArrayList<Node2>();
}
