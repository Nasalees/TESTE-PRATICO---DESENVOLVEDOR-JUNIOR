package devjunior.com.testepratico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
public class Profissional {

    @Id
    @GeneratedValue //(strategy = GenerationType.IDENTITY)
    @UuidGenerator
    private String id;

    private String nome;
    private String especialidade;
}
