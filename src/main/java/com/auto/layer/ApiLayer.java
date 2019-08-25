package com.auto.layer;

import com.auto.structure.Base;
import com.auto.structure.Layer;
import com.auto.utils.NamingStrategy;
import lombok.Data;

@Data
public class ApiLayer extends Layer {
    private String layerName = "api";
    private String[] names = new String[]{"dto", "service"};
    private String[] classNames;

    public ApiLayer(String[] classNames) {
        super(classNames);
        this.classNames = classNames;
    }

    public String getLayerContent(String project, String layer, String className) {
        if (layer.equals("dto")) {
            return "package " + Base.basePackage + "." + project + "." + layer + ";\n" +
                    "\n" +
                    "import lombok.Data;\n" +
                    "\n" +
                    "import java.io.Serializable;\n" +
                    "\n" +
                    "@Data\n" +
                    "public class " + NamingStrategy.capitalFirst(className) + NamingStrategy.capitalFirst(layer) + " implements Serializable {\n" +
                    "}";
        } else if (layer.equals("service")) {
            return "package " + Base.basePackage + "." + project + "." + layer + ";\n" +
                    "public interface " + NamingStrategy.capitalFirst(className) + NamingStrategy.capitalFirst(layer) + "  {\n" +
                    "}";
        } else
            return "";

    }

    public String getDependicies(String poroject)  {
        return "";
    }
}
