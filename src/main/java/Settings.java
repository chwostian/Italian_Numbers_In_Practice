import java.time.LocalDate;

public class Settings {
    private Long number;
    private Double speedRate;
    private LocalDate date;
    private Byte result;

    public Settings() {
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Double getSpeedRate() {
        return speedRate;
    }

    public void setSpeedRate(Double speedRate) {
        this.speedRate = speedRate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Byte getResult() {
        return result;
    }

    public void setResult(Byte result) {
        this.result = result;
    }
}
