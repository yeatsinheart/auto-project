package com.auto.layer;

import com.auto.structure.Base;
import com.auto.structure.Layer;
import com.auto.utils.NamingStrategy;
import lombok.Data;

@Data
public class PersistenceLayer  extends Layer {
    private String layerName="persistence";
    private  String[] names=new String[]{"mapper","entity"};
    private String[] classNames;

    public PersistenceLayer(String[] classNames) {
        super(classNames);
        this.classNames = classNames;
    }
    public String getLayerContent(String project,String layer,String className){
        if (layer.equals("entity")) {
            return "package " + Base.basePackage + "." + project + "." + layer + ";\n" +
                    "public class " + NamingStrategy.capitalFirst(className) + "  {\n" +
                    "}\n";
        } else if (layer.equals("mapper")) {
            return "package "+ Base.basePackage+"."+project+"."+layer+";\n" +
                    "public class "+ NamingStrategy.capitalFirst(className)+NamingStrategy.capitalFirst(layer)+" {\n" +
                    "}\n";
        } else return "";

    }
    public String getDependicies(String poroject) {
        return "<dependencies>\n" +
                "        <dependency>\n" +
                "            <groupId>"+Base.basePackage+"</groupId>\n" +
                "            <artifactId>"+poroject+"-api</artifactId>\n" +
                "            <version>1.0.0</version>\n" +
                "        </dependency>\n" +
                "    </dependencies>\n";
    }
}
