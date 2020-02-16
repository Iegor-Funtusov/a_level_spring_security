package ua.com.alevel.security.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="roles")
public class Role extends AbstractEntity {

    private static final long serialVersionUID = -8850829473386622430L;

    @Column(nullable=false, unique=true)
    @NotEmpty
    private String name;

    @ManyToMany(mappedBy="roles")
    private List<User> users;
}
