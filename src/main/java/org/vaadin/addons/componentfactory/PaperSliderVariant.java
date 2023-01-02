package org.vaadin.addons.componentfactory;

public enum PaperSliderVariant {
    LUMO_PRIMARY("primary"), LUMO_SUCCESS("success"), LUMO_ERROR(
            "error"), LUMO_SECONDARY("secondary");

    private final String variant;

    PaperSliderVariant(String variant) {
        this.variant = variant;
    }

    /**
     * Gets the variant name.
     *
     * @return variant name
     */
    public String getVariantName() {
        return variant;
    }
}