package com.springboot.wine.store.entities;


import com.springboot.wine.store.entities.listener.BaseEntityListener;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@EntityListeners(BaseEntityListener.class)
public abstract class BaseEntity {

    private long version;
    private Date createDatetime;
    private Date modifiedDatetime;

    @Column(name = "LOCK_VERSION")
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Column(name = "CREATED_DATE")
    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }


    @Column(name = "MODIFIED_DATE")
    public Date getModifiedDatetime() {
        return modifiedDatetime;
    }

    public void setModifiedDatetime(Date modifiedDatetime) {
        this.modifiedDatetime = modifiedDatetime;
    }
}
