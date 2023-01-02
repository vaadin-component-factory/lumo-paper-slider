package org.vaadin.addons.componentfactory;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.vaadin.flow.component.customfield.CustomField;

/**
 * A slider field component.
 */
public class PaperSlider extends CustomField<Integer> {

    PaperSliderComponent slider = new PaperSliderComponent();

    /**
     * Default constructor
     */
    public PaperSlider() {
        this(null);
    }

    /**
     * Create slider with label.
     * 
     * @param label
     *            The label.
     */
    public PaperSlider(String label) {
        setLabel(label);
        slider.setWidth("100%");
        add(slider);
    }

    /**
     * Set the minimum value of the range.
     * 
     * @param min
     *            int value
     */
    public void setMin(int min) {
        slider.setMin(min);
    }

    /**
     * Get current minimum value
     * 
     * @return int value
     */
    public int getMin() {
        return slider.getMin();
    }

    /**
     * Set maximum value of the slider range
     * 
     * @param max
     *            int value
     */
    public void setMax(int max) {
        slider.setMax(max);
    }

    /**
     * Get current maximum value
     * 
     * @return value
     */
    public int getMax() {
        return slider.getMax();
    }

    /**
     * Set secondary progress indicator
     * 
     * @param secondary
     *            int value between min and max
     */
    public void setSecondaryProgress(int secondary) {
        if (secondary < getMin() || secondary > getMax()) {
            throw new IllegalArgumentException(
                    "Value " + secondary + " is not in min " + getMin()
                            + " - max " + getMax() + " range");
        }
        slider.setSecondaryProgress(secondary);
    }

    /**
     * Get value of the secondary progress indicator
     * 
     * @return int value between min and max
     */
    public int getSecondaryProgress() {
        return slider.getSecondaryProgress();
    }

    /**
     * Enable pin marker
     * 
     * @param pin
     *            use true to enable pin, false to disable
     */
    public void setPinned(boolean pin) {
        slider.setPinned(pin);
    }

    /**
     * Get pin state
     * 
     * @param pin
     *            boolean value
     */
    public void isPinned(boolean pin) {
        slider.isPinned();
    }

    /**
     * Enable snaps
     * <p>
     * See: setMaxMarkers
     * 
     * @param snaps
     *            use true to enable markers
     */
    public void setSnaps(boolean snaps) {
        slider.setSnaps(snaps);
    }

    /**
     * Get marker state
     * 
     * @return boolean value
     */
    public boolean hasSnaps() {
        return slider.hasSnaps();
    }

    /**
     * Set maximum number of markers
     * 
     * @param markers
     *            int value between 0 and length of range
     */
    public void setMaxMarkers(int markers) {
        if ((markers < 0) || markers > (getMax() - getMin())) {
            throw new IllegalArgumentException("Value " + markers
                    + " is not in min 0 - " + (getMax() - getMin()) + " range");
        }
        slider.setMaxMarkers(markers);
    }

    /**
     * Get maximum number of markers
     * 
     * @return int value
     */
    public int getMaxMarkers() {
        return slider.setMaxMarkers();
    }

    @Override
    protected Integer generateModelValue() {
        return slider.getValue();
    }

    @Override
    protected void setPresentationValue(Integer value) {
        slider.setValue(value);
    }

    @Override
    public void setValue(Integer value) {
        if (value < getMin() || value > getMax()) {
            throw new IllegalArgumentException(
                    "Value " + value + " is not in min " + getMin() + " - max "
                            + getMax() + " range");
        }
        super.setValue(value);
        slider.setValue(value);
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        super.setReadOnly(readOnly);
        getElement().setAttribute("readonly", readOnly);
        slider.getElement().setAttribute("disabled", readOnly);
    }

    /**
     * Adds theme variants to the component.
     *
     * @param variants
     *            theme variants to add
     */
    public void addThemeVariants(PaperSliderVariant... variants) {
        slider.getThemeNames()
                .addAll(Stream.of(variants)
                        .map(PaperSliderVariant::getVariantName)
                        .collect(Collectors.toList()));
    }

    /**
     * Removes theme variants from the component.
     *
     * @param variants
     *            theme variants to remove
     */
    public void removeThemeVariants(PaperSliderVariant... variants) {
        slider.getThemeNames()
                .removeAll(Stream.of(variants)
                        .map(PaperSliderVariant::getVariantName)
                        .collect(Collectors.toList()));
    }
}
