package com.tigerbk.project1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class TbBoardFollowId implements Serializable {
    private static final long serialVersionUID = 2119996364901296693L;
    @Size(max = 100)
    @NotNull
    @Column(name = "CUST_ID", nullable = false, length = 100)
    private String custId;

    @Size(max = 100)
    @NotNull
    @Column(name = "FOLLOW_CUST_ID", nullable = false, length = 100)
    private String followCustId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TbBoardFollowId entity = (TbBoardFollowId) o;
        return Objects.equals(this.followCustId, entity.followCustId) &&
                Objects.equals(this.custId, entity.custId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(followCustId, custId);
    }

}