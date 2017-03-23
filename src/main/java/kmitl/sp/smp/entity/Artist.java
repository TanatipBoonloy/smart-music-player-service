package kmitl.sp.smp.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Jo on 3/24/2017.
 */
@Data
@Table
@Entity
public class Artist {
    @Id
    @Column(name = "artist")
    private String Artist;
}
