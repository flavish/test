package flavish.test.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@MappedSuperclass
class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "key", unique = true, nullable = false)
    private String key;

    @Column(name = "summary")
    private String summary;
}
