package Hello;

import javax.validation.constraints.NotNull;

public class Citizen {
    @NotNull
    private String Name;
    @CheckAccount
    private RegularAmount OutGoing;
    @CheckAccount
    private RegularAmount Income;

    public Citizen(String name, RegularAmount outGoing, RegularAmount income) {
        this.setName(name);
        this.setIncome(income);
        this.setOutGoing(outGoing);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


    public RegularAmount getOutGoing() {
        return OutGoing;
    }

    public void setOutGoing(RegularAmount outGoing) {
        OutGoing = outGoing;
    }

    public RegularAmount getIncome() {
        return Income;
    }

    public void setIncome(RegularAmount income) {
        Income = income;
    }

    @Override
    public String toString() {
        return "Citizen [Name=" + Name + ", OutGoing=" + OutGoing + ", Income=" + Income
                + "]";
    }


}
