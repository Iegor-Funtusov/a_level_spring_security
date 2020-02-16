package ua.com.alevel.security.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="users")
public class User extends AbstractEntity {

    private static final long serialVersionUID = 5161486496378466495L;

    @Column(nullable=false)
    @NotEmpty
    private String name;

    @Column(nullable=false, unique=true)
    @NotEmpty
    @Email(message="{errors.invalid_email}")
    private String email;

    @Column(nullable=false)
    @NotEmpty
    @Size(min=4)
    private String password;

    @ManyToMany(cascade= CascadeType.MERGE)
    @JoinTable(
            name="user_role",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List<Role> roles;
}
