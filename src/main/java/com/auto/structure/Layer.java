package com.auto.structure;

import com.auto.utils.FileUtil;
import com.auto.utils.NamingStrategy;
import lombok.Data;

@Data
public class Layer {
    private String layerName = "layer";
    private String[] names = new String[]{"dto", "service"};
    private String[] classNames;

    public Layer(String[] classNames) {
        this.classNames = classNames;
    }

    public void initFile(String project, String path) {
        for (String className : this.getClassNames()) {
            for (String layer : this.getNames()) {
                if (this.getLayerName().equals("service") && layer.equals("service")) continue;
                if (this.getLayerName().equals("persistence") && layer.equals("entity")) {
                    String modulePomPath4 = path + "/" + layer.replaceAll("\\.", "/") + "/" + NamingStrategy.capitalFirst(className) + ".java";
                    FileUtil.createFile(modulePomPath4);
                    FileUtil.WriteStringToFile(modulePomPath4, getLayerContent(project, layer, className));
                } else {
                    String modulePomPath4 = path + "/" + layer.replaceAll("\\.", "/") + "/" + NamingStrategy.capitalFirst(className) + NamingStrategy.capitalFirst(layer.replaceAll("\\.impl", "Impl")) + ".java";
                    FileUtil.createFile(modulePomPath4);
                    FileUtil.WriteStringToFile(modulePomPath4, getLayerContent(project, layer, className));

                }
            }
        }
    }

    public String getLayerContent(String project, String layer, String className) {
        return "";
    }

    public String getLayerPomContent(String project) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <parent>\n" +
                "        <artifactId>" + project + "</artifactId>\n" +
                "        <groupId>" + Base.basePackage + "</groupId>\n" +
                "        <version>" + Base.baseVersion + "</version>\n" +
                "    </parent>\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "\n" +
                "    <artifactId>" + project + "-" + this.getLayerName() + "</artifactId>\n" +
                getDependicies(project) +
                "</project>";
    }

    public String getDependicies(String poroject) {
        return "";
    }
}
