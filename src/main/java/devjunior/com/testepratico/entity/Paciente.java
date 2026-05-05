package devjunior.com.testepratico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.processing.Pattern;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
public class Paciente {

    @Id
    @GeneratedValue //(strategy = GenerationType.IDENTITY)
    @UuidGenerator
    private String id;

    private String nomeCompleto;

    private LocalDate dataNascimento;

    private String cpf;

    private String email;


}
