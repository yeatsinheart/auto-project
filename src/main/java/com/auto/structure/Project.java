package com.auto.structure;

import com.auto.utils.FileUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Project {
    private String name;
    private List<Module> modules;

    public Project(String name,  Layer[] layers,String[] classNames) {
        this.name = name;
        List<Module> list = new ArrayList<>();
        for (Layer layer : layers) {
            list.add(new Module(name, layer));
        }
        this.modules = list;
    }

    public static String getProjectFtl(Project project) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <parent>\n" +
                "        <artifactId>" + Base.baseArt + "</artifactId>\n" +
                "        <groupId>" + Base.basePackage + "</groupId>\n" +
                "        <version>" + Base.baseVersion + "</version>\n" +
                "    </parent>\n" +
                "    <modelVersion>4.0.0</modelVersion>\n<packaging>pom</packaging>" +
                "\n" +
                "    <artifactId>" + project.getName() + "</artifactId>\n" +
                "    <modules>\n" +
                project.getModuleFtl() +
                "    </modules>\n" +
                "</project>";
    }

    public String getModuleFtl() {
        String s = "";
        for (Module m : this.modules) {
            s += "<module>" + this.getName() + "-" + m.getLayer().getLayerName() + "</module>\n";
        }
        return s;
    }

    public void initFile() {
        String modulePomPath4 = name + "/pom.xml";
        FileUtil.createFile(modulePomPath4);
        FileUtil.WriteStringToFile(modulePomPath4, getProjectFtl(this));
    }


}
