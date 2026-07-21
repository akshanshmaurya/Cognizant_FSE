package com.dsa.forecast;

public class FinancialForecaster {

    /**
     * Recursive Calculation of Future Value:
     * FV = PV * (1 + rate)^periods
     * Time Complexity: O(periods) - Simple linear recursion depth.
     */
    public static double predictFutureValue(double currentValue, double growthRate, int periods) {
        if (periods == 0) {
            return currentValue;
        }
        return predictFutureValue(currentValue * (1 + growthRate), growthRate, periods - 1);
    }

    public static void main(String[] args) {
        double initialValue = 10000.00; // $10,000
        double annualGrowthRate = 0.07;  // 7% annual growth
        int forecastYears = 5;

        double futureValue = predictFutureValue(initialValue, annualGrowthRate, forecastYears);
        System.out.println(String.format("Initial Investment: $%.2f", initialValue));
        System.out.println(String.format("Annual Growth Rate: %.1f%%", annualGrowthRate * 100));
        System.out.println("Forecast Period: " + forecastYears + " years");
        System.out.println(String.format("Predicted Future Value: $%.2f", futureValue));
    }
}
