package net.meander.subtlyd.util;

/**
 * @see net.minecraft.util.Mth
 */
public class MthSD {
    public static final double[] POWER_OF_2_STEPS = { 0.0625, 0.125, 0.25, 0.5, 1.0, 2.0, 4.0, 8.0, 16.0 };

    public static double roundToTenThousandths(double value) {
        return Math.round(value * 10000.0) / 10000.0;
    }

    public static double snapToPowerOf2(double value, double minValue, double maxValue) {
        double nearestPow = POWER_OF_2_STEPS[0];
        double minDiff = Math.abs(value - nearestPow);

        for (double step : POWER_OF_2_STEPS) {
            double diff = Math.abs(value - step);

            if (diff < minDiff) {
                minDiff = diff;
                nearestPow = step;
            }
        }

        return Math.clamp(nearestPow, minValue, maxValue);
    }
}