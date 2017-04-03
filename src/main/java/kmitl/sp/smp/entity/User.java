package kmitl.sp.smp.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Jo on 4/3/2017.
 */
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "facebook_id")
    private String facebookId;
}
