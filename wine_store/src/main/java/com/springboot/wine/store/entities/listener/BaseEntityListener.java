package com.springboot.wine.store.entities.listener;

import com.springboot.wine.store.entities.BaseEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;
import java.util.Objects;

public class BaseEntityListener {

    @PrePersist
    public void prePersist(Object object) {


        if (object instanceof BaseEntity) {

            BaseEntity entity = (BaseEntity) object;
            if(Objects.isNull(entity.getCreateDatetime()))
                entity.setCreateDatetime(new Date());
            entity.setModifiedDatetime(new Date());

            entity.setVersion(1L);

        }
    }

    @PreUpdate
    public void preUpdate(Object object) {
        if (object instanceof BaseEntity) {

            BaseEntity entity = (BaseEntity) object;
            entity.setModifiedDatetime(new Date());
            entity.setVersion(entity.getVersion() + 1L);

        }
    }
}
