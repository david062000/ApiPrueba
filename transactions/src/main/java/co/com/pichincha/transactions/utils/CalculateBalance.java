package co.com.pichincha.transactions.utils;

import org.springframework.stereotype.Component;

@Component
public class CalculateBalance {
    public Double calculate(Double currentBalance, Double value, String motionType) {
        if (motionType.equalsIgnoreCase("R")) {
            return currentBalance-value;
        }
        return currentBalance+value;
    }

    public Boolean allowOperation(Double currentBalance, Double value, String motionType) {
        if (motionType.equalsIgnoreCase("R") && (value>currentBalance)) {
            return false;
        }
        return true;
    }
}
