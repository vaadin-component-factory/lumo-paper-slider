package org.vaadin.addons.componentfactory;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("slider")
public class SliderView extends VerticalLayout {

    public SliderView() {
        setSizeFull();

        PaperSlider slider1 = new PaperSlider("Do you like this component?");
        PaperSlider slider2 = new PaperSlider();
        PaperSlider slider3 = new PaperSlider("Disabled");
        PaperSlider slider4 = new PaperSlider("Read only");
        PaperSlider slider5 = new PaperSlider("Secondary");

        slider1.setMin(1);
        slider1.setMax(10);
        slider1.setWidth("500px");
        slider1.addValueChangeListener(event -> {
            Notification.show("Answer: " + event.getValue());
        });
        slider1.focus();
        slider1.addFocusShortcut(Key.KEY_F, KeyModifier.CONTROL);

        slider2.setMin(0);
        slider2.setMax(100);
        slider2.setWidth("250px");
        slider2.setValue(0);
        slider2.addThemeVariants(PaperSliderVariant.LUMO_SECONDARY);
        slider2.addValueChangeListener(event -> {
            Notification.show("Value: " + event.getValue());
        });
        slider2.addFocusListener(event -> {
            Notification.show("Focused");
        });
        slider2.addBlurListener(event -> {
            Notification.show("Blurred");
        });             

        slider3.setMin(1);
        slider3.setMax(10);
        slider3.setWidth("500px");
        slider3.setValue(4);
        slider3.setEnabled(false);

        slider4.setMin(0);
        slider4.setMax(10);
        slider4.setWidth("500px");
        slider4.setValue(0);
        slider4.setReadOnly(true);

        slider5.setMin(0);
        slider5.setMax(100);
        slider5.setWidth("500px");
        slider5.setValue(13);
        slider5.setSecondaryProgress(62);
        slider5.setPinned(true);
        slider5.setSnaps(true);
        slider5.setMaxMarkers(10);
        slider5.setTabIndex(-1);

        Button button = new Button();
        button.addClickListener(e -> {
            slider1.setValue(5);
        });

        add(slider1, slider2, slider3, slider4, slider5, button);
    }
}
