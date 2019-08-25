package com.auto.structure;

import com.auto.utils.FileUtil;
import lombok.Data;

@Data
public class Module {


    private String project;
    private Layer layer;

    public Module(String project, Layer layer) {
        this.project = project;
        this.layer = layer;
    }

    public String getModuleFtl(Module module) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <parent>\n" +
                "        <artifactId>" + module.getProject() + "</artifactId>\n" +
                "        <groupId>" + Base.basePackage + "</groupId>\n" +
                "        <version>" + Base.baseVersion + "</version>\n" +
                "    </parent>\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "    <artifactId>" + module.getProject() + "-" + module.getLayer().getLayerName() + "</artifactId>\n" +
                "</project>";
    }

    public void initFile(String basePackage) {
        String moduleJavaMenuPath1 = project + "/" + project + "-" + this.getLayer().getLayerName() + "/src/main/java/" + basePackage.replaceAll("\\.", "/") + "/" + project;
        FileUtil.mkDir(moduleJavaMenuPath1);
        String moduleTestMenuPath1 = project + "/" + project + "-" + this.getLayer().getLayerName() + "/src/test/java/" + basePackage.replaceAll("\\.", "/") + "/" + project ;
        FileUtil.mkDir(moduleTestMenuPath1);
        String moduleResourceMenuPath1 = project + "/" + project + "-" + this.getLayer().getLayerName() + "/src/main/resources";
        FileUtil.mkDir(moduleResourceMenuPath1);
        String modulePomPath4 = project + "/" + project + "-" + this.getLayer().getLayerName() + "/pom.xml";
        FileUtil.createFile(modulePomPath4);
        FileUtil.WriteStringToFile(modulePomPath4, this.getLayer().getLayerPomContent(project));
        layer.initFile(project,moduleJavaMenuPath1);
    }




}
