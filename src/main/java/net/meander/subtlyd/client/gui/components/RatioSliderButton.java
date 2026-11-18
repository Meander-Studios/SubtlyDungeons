package net.meander.subtlyd.client.gui.components;

import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;
import java.util.function.Consumer;

public class RatioSliderButton extends AbstractSliderButton {
    private final Component prefix;
    private final double[] steps;
    private final Consumer<Double> onValueChanged;

    public RatioSliderButton(int x, int y, int width, int height, Component prefix, double[] steps, double currentValue, Consumer<Double> onValueChanged) {
        super(x, y, width, height, Component.empty(), getInitialRatio(currentValue, steps));
        this.prefix = prefix;
        this.steps = steps;
        this.onValueChanged = onValueChanged;

        updateMessage();
    }

    private static double getInitialRatio(double currentValue, double[] steps) {
        int index = getNearestStepIndex(currentValue, steps);

        return steps.length > 1 ? (double) index / (steps.length - 1) : 0.0;
    }

    private static int getNearestStepIndex(double value, double[] steps) {
        int nearestIndex = 0;
        double minDiff = Math.abs(value - steps[0]);

        for (int i = 0; i < steps.length; i++) {
            double diff = Math.abs(value - steps[i]);

            if (diff < minDiff) {
                minDiff = diff;
                nearestIndex = i;
            }
        }

        return nearestIndex;
    }

    private double getSnappedValue() {
        int index = (int) Math.round(value * (steps.length - 1));
        index = Math.clamp(index, 0, steps.length - 1);

        return steps[index];
    }

    @Override
    protected void updateMessage() {
        double realValue = getSnappedValue();
        String formattedValue;

        if (realValue < 1.0) {
            int denominator = (int) Math.round(1.0 / realValue);

            formattedValue = "1/" + denominator;
        } else if (realValue == Math.floor(realValue)) {
            formattedValue = String.valueOf((int) realValue);
        } else {
            formattedValue = String.valueOf(realValue);
        }

        setMessage(Component.literal(String.format("%s: %s", prefix.getString(), formattedValue)));
    }

    @Override
    protected void applyValue() {
        double realValue = getSnappedValue();

        onValueChanged.accept(realValue);
    }

    public void setRatioValue(final double newValue) {
        int index = getNearestStepIndex(newValue, steps);
        value = steps.length > 1 ? (double) index / (steps.length - 1) : 0.0;

        updateMessage();
    }
}