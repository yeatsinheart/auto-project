package com.auto.layer;

import com.auto.structure.Base;
import com.auto.structure.Layer;
import com.auto.utils.NamingStrategy;
import lombok.Data;

@Data
public class WebLayer extends Layer {
    private String layerName = "web";
    private String[] names = new String[]{"controller"};
    private String[] classNames;

    public WebLayer(String[] classNames) {
        super(classNames);
        this.classNames = classNames;
    }

    public String getLayerContent(String project, String layer, String className) {
        return "package " + Base.basePackage + "." + project + "." + layer + ";\n" +
                "import org.springframework.web.bind.annotation.RestController;\n" +
                "\n" +
                "@RestController\n" +
                "public class " + NamingStrategy.capitalFirst(className) + NamingStrategy.capitalFirst(layer) + " {\n" +
                "}\n";
    }

    public String getDependicies(String poroject) {
        return "<dependencies>\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework.boot</groupId>\n" +
                "            <artifactId>spring-boot-starter-web</artifactId>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>" + Base.basePackage + "</groupId>\n" +
                "            <artifactId>" + poroject + "-api</artifactId>\n" +
                "            <version>1.0.0</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>" + Base.basePackage + "</groupId>\n" +
                "            <artifactId>" + poroject + "-service</artifactId>\n" +
                "            <version>1.0.0</version>\n" +
                "        </dependency>\n" +
                "    </dependencies>\n";
    }
}
