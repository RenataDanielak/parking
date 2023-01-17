import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public MiejsceRepozytorium miejsceRepozytorium(){
        return new MiejsceRepozytorium();
    }

    @Bean
    public RezerwacjaRepozytorium rezerwacjaRepozytorium(){
        return new RezerwacjaRepozytorium();
    }

    @Bean
    public ParkingSerwis parkingSerwis(MiejsceRepozytorium miejsceRepozytorium, RezerwacjaRepozytorium rezerwacjaRepozytorium){
        return new ParkingSerwis(miejsceRepozytorium, rezerwacjaRepozytorium);
    }


}
