package org.vaadin.addons.componentfactory;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

@Route("binder")
public class BinderView extends VerticalLayout {

    public BinderView() {
        PaperSlider slider = new PaperSlider();
        slider.setLabel("Value");
        slider.setMin(0);
        slider.setMax(10);
        slider.setWidth("100px");
        Binder<Value> binder = new Binder<>();
        binder.forField(slider)
                .withValidator(value -> value > 5, "Give more than 5")
                .bind(Value::getValue, Value::setValue);
        binder.readBean(new Value(null));

        add(slider);
        add(new Button("Reset", e -> slider.clear()));
    }

    public class Value {
        private Integer value;

        public Value(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }
}
