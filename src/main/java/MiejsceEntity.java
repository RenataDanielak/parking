import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="miejsce")

public class MiejsceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="numer_miejsca")
    private Integer numerMiejsca;
    @OneToMany(mappedBy = "miejsce", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<RezerwacjaEntity> rezerwacje;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumerMiejsca() {
        return numerMiejsca;
    }

    public void setNumerMiejsca(Integer numerMiejsca) {
        this.numerMiejsca = numerMiejsca;
    }

    public List<RezerwacjaEntity> getRezerwacje() {
        return rezerwacje;
    }

    public void setRezerwacje(List<RezerwacjaEntity> rezerwacje) {
        this.rezerwacje = rezerwacje;
    }
}
