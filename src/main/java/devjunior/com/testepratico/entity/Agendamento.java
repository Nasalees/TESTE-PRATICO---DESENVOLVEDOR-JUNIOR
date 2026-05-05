package devjunior.com.testepratico.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


@Entity
public class Agendamento {

    @Id
    @GeneratedValue //(strategy = GenerationType.IDENTITY)
    @UuidGenerator
    private String id;

    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private TipoAgendamento tipoAgendamento;

    @ManyToOne
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;

    private String motivoCancelamento;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;


}
