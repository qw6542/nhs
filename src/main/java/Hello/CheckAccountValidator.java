package Hello;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;


public class CheckAccountValidator implements ConstraintValidator<CheckAccount, RegularAmount> {

    // String pattern for whole number or number with up to two decimal places
    public final String AmountPattern = "([1-9][0-9]*|[1-9][0-9]*\\.[0-9]{1,2})";

    @Override
    public void initialize(CheckAccount annotation) {
    }
    @Override
    public boolean isValid(RegularAmount  regularAmount, ConstraintValidatorContext  constraintValidatorContext) {

        // Amount of null is not valid
        if ( regularAmount == null || regularAmount.getAmount() == null ) {
            return false;
        }

        // Check if Amount match the accept current pattern
        if (!regularAmount.getAmount().matches(AmountPattern)){
            return false;
        }
        //  Monthly incomes will not divide to a whole weekly value
        if(Frequency.MONTH == regularAmount.getFrequency()){
            return false;
        }
        return isExactWholeWeekValue(regularAmount);
    }
       // Check if it is multiple of a whole number of pence when divided to a one week frequency
       public boolean isExactWholeWeekValue(RegularAmount regularAmount) {

        BigDecimal amount = new BigDecimal(regularAmount.getAmount());
        int numberofweeks = regularAmount.getFrequency().getNumberOfWeeks();

        //multiply by 100 and find the remainder
        BigDecimal result = amount.multiply(BigDecimal.valueOf(100)).remainder(BigDecimal.valueOf(numberofweeks));

        // remainder equals 0 indicate the exact value
           if (result.compareTo(BigDecimal.ZERO) ==0){
            return true;
        }else {
            return false;
        }
       }

}
