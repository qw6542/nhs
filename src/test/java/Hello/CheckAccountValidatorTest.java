package Hello;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

public class CheckAccountValidatorTest {

    private Validator validator;

    @Before
    public void init() {

        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        this.validator = vf.getValidator();

    }
    @Test
    public void run () {
      shouldPass(Frequency.WEEK,"Alice","30.15","123456.50");
        shouldPass(Frequency.WEEK,"Alice","4565.00","21456.31");
        shouldPass(Frequency.WEEK,"Alice","12350.1","89.13");
        shouldPass(Frequency.WEEK,"Alice","2354.0","4568787.12");

      shouldPass(Frequency.TWO_WEEK,"Bob","5456456.16","5645655.24");
        shouldPass(Frequency.TWO_WEEK,"Bob","456456.22","5645655.10");
        shouldPass(Frequency.TWO_WEEK,"Bob","1231123123123.38","5645655.32");
        shouldPass(Frequency.TWO_WEEK,"Bob","4564545.48","5645655.38");

      shouldPass(Frequency.FOUR_WEEK,"Charlie","4568798789.56","456.24");
        shouldPass(Frequency.FOUR_WEEK,"Charlie","123.12","456.52");
        shouldPass(Frequency.FOUR_WEEK,"Charlie","4568798789.56","456.32");
        shouldPass(Frequency.FOUR_WEEK,"Charlie","4568798789.48","456.72");
        shouldPass(Frequency.FOUR_WEEK,"Charlie","4568798789.96","456.64");

      shouldPass(Frequency.QUARTER,"Delta","130.13","260.26");
        shouldPass(Frequency.QUARTER,"Delta","163.15","3323.58");
        shouldPass(Frequency.QUARTER,"Delta","7078.50","7181.20");
        shouldPass(Frequency.QUARTER,"Delta","332.15","3319.29");
        shouldPass(Frequency.QUARTER,"Delta","33.15","7465.25");

      shouldPass(Frequency.YEAR,"Franky","1040.52","520.00");
      shouldPass(Frequency.YEAR,"Franky","65.00","27172.60");
        shouldPass(Frequency.YEAR,"Franky","2831.92","1276.60");
        shouldPass(Frequency.YEAR,"Franky","66383.20","2887.04");
        shouldPass(Frequency.YEAR,"Franky","150126.08","2895.88");

      // Monthly will not divide to a whole weekly value.  Whatever the amount will failed
      shouldFail(Frequency.MONTH,"George","1040.52","520.00");
      shouldFail(Frequency.MONTH,"George","001040.52","520.00");

      //Failed By amount of null
      shouldFail(Frequency.WEEK,"Henry",null,"520.00");

      //Failed for amount of 3 decimal places
      shouldFail(Frequency.WEEK,"Ivy","30.151","1.00");

      //Failed for cannot divided by two weeks
      shouldFail(Frequency.TWO_WEEK,"Jack","30.21","131.00");

      //Failed By cannot divided by four weeks
      shouldFail(Frequency.FOUR_WEEK,"Lima","30.33","545.00");

      //Failed By cannot divided by 13 weeks
      shouldFail(Frequency.QUARTER,"Micheal","54456.33","87892.54");

      //Failed By cannot divided by 52 weeks
      shouldFail(Frequency.YEAR,"Nick","1321345.33","456785.00");
    }

    public void shouldPass (Frequency frequency,String name, String amount1,String amount2) {
        RegularAmount income = new RegularAmount(frequency, amount1);
        RegularAmount outgoing = new RegularAmount(frequency, amount2);
        Citizen citizen = new Citizen(name,income,outgoing);
        Set<ConstraintViolation<Citizen>> violations = this.validator.validate(citizen);
        assertTrue(violations.size() == 0);
        assertTrue(citizen.getName()== name);
    }

    public void shouldFail (Frequency frequency,String name, String amount1,String amount2) {
        RegularAmount income = new RegularAmount(frequency, amount1);
        RegularAmount outgoing = new RegularAmount(frequency, amount2);
        Citizen citizen = new Citizen(name,income,outgoing);
        Set<ConstraintViolation<Citizen>> violations = this.validator.validate(citizen);
        assertFalse(violations.size() == 0);
    }
}