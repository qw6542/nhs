package Hello;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;


public class RegularAmount {
    @NotNull
    @Valid
    private Frequency frequency;
    private String amount;

    public RegularAmount(Frequency frequency, String amount) {
        setFrequency(frequency);
        setAmount(amount);
    }

    @NotNull
    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "RegularAmount [frequency=" + frequency + ", amount=" + amount + "]";
    }

    @NotNull
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
