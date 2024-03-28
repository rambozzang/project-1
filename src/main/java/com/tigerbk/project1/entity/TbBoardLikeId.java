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
public class TbBoardLikeId implements Serializable {
    private static final long serialVersionUID = 5743013012641032912L;
    @NotNull
    @Column(name = "BOARD_ID", nullable = false)
    private Long boardId;

    @Size(max = 100)
    @NotNull
    @Column(name = "CUST_ID", nullable = false, length = 100)
    private String custId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TbBoardLikeId entity = (TbBoardLikeId) o;
        return Objects.equals(this.custId, entity.custId) &&
                Objects.equals(this.boardId, entity.boardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(custId, boardId);
    }

}