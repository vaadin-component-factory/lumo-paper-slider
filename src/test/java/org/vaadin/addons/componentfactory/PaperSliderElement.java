package org.vaadin.addons.componentfactory;

import java.util.List;

import com.vaadin.flow.component.html.testbench.DivElement;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("paper-slider")
public class PaperSliderElement extends TestBenchElement {

    public void setValue(int value) {
        setProperty("value", value);
    }

    public int getValue() {
        return getPropertyInteger("value");
    }

    public TestBenchElement getProgressElement() {
        return this.$("paper-progress").first();
    }

    public List<DivElement> getSliderMarkers() {
        return this.$(DivElement.class).attribute("class", "slider-marker")
                .all();
    }

}
