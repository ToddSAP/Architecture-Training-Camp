package windows;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWindowComponent implements WindowComponent{
    String label;
    List<AbstractWindowComponent> childrenComponent = new ArrayList<>();

    @Override
    public void operation () {
        System.out.println("加载组件 [" + this.label + "]");
        childrenComponent.forEach(AbstractWindowComponent::operation);
    }

    @Override
    public void addChild(WindowComponent component) {
        childrenComponent.add((AbstractWindowComponent) component);
    }
}
