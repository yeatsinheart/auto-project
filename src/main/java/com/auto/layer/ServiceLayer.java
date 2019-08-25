package com.auto.layer;

import com.auto.structure.Base;
import com.auto.structure.Layer;
import com.auto.utils.NamingStrategy;
import lombok.Data;

@Data
public class ServiceLayer extends Layer {
    private String layerName = "service";
    private String[] names = new String[]{"service", "service.impl"};
    private String[] classNames;

    public ServiceLayer(String[] classNames) {
        super(classNames);
        this.classNames = classNames;
    }

    public String getLayerContent(String project, String layer, String className) {
        if (layer.equals("service")) {
            return "package " + Base.basePackage + "." + project + "." + layer + ";\n" +
                    "public interface " + NamingStrategy.capitalFirst(className) + NamingStrategy.capitalFirst("Service") + "  {\n" +
                    "}\n";
        } else if (layer.equals("service.impl")) {
            return "package " + Base.basePackage + "." + project + "." + layer + ";\n" +
                    "import " + Base.basePackage + "." + project + ".service." + NamingStrategy.capitalFirst(className) + "Service;\n" +
                    "import lombok.extern.slf4j.Slf4j;\n" +
                    "import org.springframework.stereotype.Service;\n" +
                    "@Slf4j\n" +
                    "@Service\n" +
                    "public class " + NamingStrategy.capitalFirst(className) + NamingStrategy.capitalFirst("ServiceImpl") + " implements " + NamingStrategy.capitalFirst(className) + "Service {\n" +
                    "}\n";
        } else return "";

    }

    public String getDependicies(String poroject) {
        return "<dependencies>\n" +
                "        <dependency>\n" +
                "            <groupId>"+Base.basePackage+"</groupId>\n" +
                "            <artifactId>"+poroject+"-api</artifactId>\n" +
                "            <version>1.0.0</version>\n" +
                "        </dependency>\n" +"        <dependency>\n" +
                "            <groupId>"+Base.basePackage+"</groupId>\n" +
                "            <artifactId>"+poroject+"-persistence</artifactId>\n" +
                "            <version>1.0.0</version>\n" +
                "        </dependency>\n" +
                "    </dependencies>\n";
    }
}
