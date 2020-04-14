package app;
//import lombok.Getter;
import javax.persistence.*;

@Entity
@Table(name = "Rate")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String date;
    private Double currency;

    public Rate(){
        super();
    }

    public Rate(String date, Double currency){
        super();
        this.currency = currency;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public Double getCurrency() {
        return currency;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCurrency(Double currency) {
        this.currency = currency;
    }

}
