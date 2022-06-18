package com.bossware.app.api.shared.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass 
public class BaseEntity implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	protected long id;
	
	@Column(nullable=false)
	protected Date creationTime = new Date();
	

	protected String creatorId ;
	
	protected Date deletionTime;
	
	
	protected String deletorUserId;
	
	
	protected Date modificationTime;
	
	
	protected String modifierUserId;
	

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public Date getCreationTime() {
		return creationTime;
	}
	
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public Date getDeletionTime() {
		return deletionTime;
	}
	public void setDeletionTime() {
		this.deletionTime = new Date();
	}
	public String getDeletorUserId() {
		return deletorUserId;
	}
	public void setDeletorUserId(String deletorUserId) {
		this.deletorUserId = deletorUserId;
	}
	public Date getModificationTime() {
		return modificationTime;
	}
	public void setModificationTime() {
		this.modificationTime = new Date();
	}
	public String getModifierUserId() {
		return modifierUserId;
	}
	public void setModifierUserId(String modifierUserId) {
		this.modifierUserId = modifierUserId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(creationTime, creatorId, deletionTime, deletorUserId, id, modificationTime,
				modifierUserId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		return Objects.equals(creationTime, other.creationTime) && Objects.equals(creatorId, other.creatorId)
				&& Objects.equals(deletionTime, other.deletionTime)
				&& Objects.equals(deletorUserId, other.deletorUserId) 
				&& id == other.id && Objects.equals(modificationTime, other.modificationTime)
				&& Objects.equals(modifierUserId, other.modifierUserId);
	}
	@Override
	public String toString() {
		return "BaseEntity [id=" + id + ", entityId=" +", creationTime=" + creationTime + ", creatorId="
				+ creatorId + ", deletionTime=" + deletionTime + ", deletorUserId=" + deletorUserId
				+ ", modificationTime=" + modificationTime + ", modifierUserId=" + modifierUserId + "]";
	}
	
	
}
