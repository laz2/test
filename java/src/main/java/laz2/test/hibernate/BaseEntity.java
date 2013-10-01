package laz2.test.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Dmitry Lazurkin
 */
@MappedSuperclass
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;

	@Column(name = "version", nullable = false)
	@Version
	public Long version = new Long(1);

	public String data;
	
	public BaseEntity() {
	}

	@Override
	public final String toString() {
		ToStringBuilder b = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);

		b.append("id", id);
		b.append("version", version);

		return b.toString();
	}
	
	protected void toStringImp(ToStringBuilder b) {
	}
}
