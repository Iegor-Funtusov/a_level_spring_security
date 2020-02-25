package ua.com.alevel.security.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tokens")
public class UserToken extends AbstractEntity {
    private static final long serialVersionUID = 7555462891382898514L;

    @Column(name = "user_id", nullable = false, unique = true)
    private Integer userId;

    @Column(name = "token", nullable = false, unique = true)
    private String token;
}
