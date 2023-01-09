import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="rezerwacja")
public class RezerwacjaEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Integer id;
   @Column(name="uzytkownik")
   private String uzytkownik;
   @Column(name="od")
   private Date start;
   @Column(name="do")
   private Date koniec;
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name="miejsce_id")
   private MiejsceEntity miejsce;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getUzytkownik() {
      return uzytkownik;
   }

   public void setUzytkownik(String uzytkownik) {
      this.uzytkownik = uzytkownik;
   }

   public Date getStart() {
      return start;
   }

   public void setStart(Date start) {
      this.start = start;
   }

   public Date getKoniec() {
      return koniec;
   }

   public void setKoniec(Date koniec) {
      this.koniec = koniec;
   }

   public MiejsceEntity getMiejsce() {
      return miejsce;
   }

   public void setMiejsce(MiejsceEntity miejsce) {
      this.miejsce = miejsce;
   }
}
