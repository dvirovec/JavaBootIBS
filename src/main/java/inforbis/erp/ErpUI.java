package inforbis.erp;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI(path="/erpui")
public class ErpUI extends UI {

    private VerticalLayout root;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        root = new VerticalLayout();

        final TextField name = new TextField("Name");

        final Button save = new Button("Spremi");

        root.addComponent(name);
        root.addComponent(save);

        setContent(root);

    }
}
