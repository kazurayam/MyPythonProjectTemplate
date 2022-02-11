package flaskr.pom.actions;

import java.net.URL;
import java.util.Map;

public abstract class ActionListener {

    ActionListener() {}

    public abstract void on(Class clazz, URL url, Map<String, String> attributes);

}
