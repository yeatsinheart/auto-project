package com.auto.structure;

import com.auto.layer.ApiLayer;
import com.auto.layer.PersistenceLayer;
import com.auto.layer.ServiceLayer;
import com.auto.layer.WebLayer;
import lombok.Data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Data
public class Base {
    public static String basePath = "";
    public static String basePackage = "com.philippines";
    public static String baseArt = "project";
    public static String baseGroup = basePackage + "." + baseArt;
    public static String baseVersion = "1.0.0";

    public static String getModles(HashMap<String, String[]> models) {
        Iterator<Map.Entry<String, String[]>> it = models.entrySet().iterator();
        String s = "";
        while (it.hasNext()) {
            String key = it.next().getKey();
            s += "        <module>" + key + "</module>\n";
        }
        return s;
    }

    public static String getProjectFtl(HashMap<String, String[]> models) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "\n" +
                "    <groupId>" + basePackage + "</groupId>\n" +
                "    <artifactId>" + baseArt + "</artifactId>\n" +
                "    <packaging>pom</packaging>\n" +
                "    <version>" + baseVersion + "</version>\n" +
                "    <modules>\n" +
                getModles(models)+
                "    </modules>\n" +
                "    <properties>\n" +
                "        <java.version>1.8</java.version>\n" +
                "        <mybatisplus-spring-boot-starter.version>1.0.4</mybatisplus-spring-boot-starter.version>\n" +
                "        <mybatis-plus.version>3.1.2</mybatis-plus.version>\n" +
                "        <mysql.driver.version>8.0.15</mysql.driver.version>\n" +
                "        <swagger.version>2.9.2</swagger.version>\n" +
                "    </properties>\n" +
                "\n" +
                "    <dependencies>\n" +
                "        <dependency>\n" +
                "            <groupId>org.projectlombok</groupId>\n" +
                "            <artifactId>lombok</artifactId>\n" +
                "            <version>1.18.8</version>\n" +
                "        </dependency>\n" +
                "\n" +
                "\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework.boot</groupId>\n" +
                "            <artifactId>spring-boot-starter-aop</artifactId>\n" +
                "        </dependency>\n" +
                "        <!-- Swagger 2 -->\n" +
                "        <dependency>\n" +
                "            <groupId>io.springfox</groupId>\n" +
                "            <artifactId>springfox-swagger-ui</artifactId>\n" +
                "            <version>${swagger.version}</version>\n" +
                "        </dependency>\n" +
                "\n" +
                "        <dependency>\n" +
                "            <groupId>io.springfox</groupId>\n" +
                "            <artifactId>springfox-swagger2</artifactId>\n" +
                "            <version>${swagger.version}</version>\n" +
                "        </dependency>\n" +
                "        <!-- jdbc driver -->\n" +
                "        <dependency>\n" +
                "            <groupId>mysql</groupId>\n" +
                "            <artifactId>mysql-connector-java</artifactId>\n" +
                "            <version>${mysql.driver.version}</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>com.baomidou</groupId>\n" +
                "            <artifactId>mybatis-plus-boot-starter</artifactId>\n" +
                "            <version>${mybatis-plus.version}</version>\n" +
                "        </dependency>\n" +
                "\n" +
                "\n" +
                "    </dependencies>\n" +
                "    <dependencyManagement>\n" +
                "        <dependencies>\n" +
                "            <dependency>\n" +
                "                <groupId>org.springframework.boot</groupId>\n" +
                "                <artifactId>spring-boot-dependencies</artifactId>\n" +
                "                <version>2.1.7.RELEASE</version>\n" +
                "                <type>pom</type>\n" +
                "                <scope>import</scope>\n" +
                "            </dependency>\n" +
                "        </dependencies>\n" +
                "    </dependencyManagement>\n" +
                "\n" +
                "</project>";
    }

    public static void generate(String projectName, Layer[] layers, String[] classNames) {
        /**初始化项目结构*/
        Project project = new Project(projectName, layers, classNames);
        for (Module module : project.getModules()) {
            module.initFile(Base.basePackage);
        }
        project.initFile();
    }
    public static Layer[] getWebLayer(String[] classNames) {
        Layer[] webLayers = {new ApiLayer(classNames), new WebLayer(classNames)};
        return webLayers;
    }
    public static Layer[] getServiceLayers(String[] classNames) {
        Layer[] serviceLayers = {new ApiLayer(classNames), new ServiceLayer(classNames), new PersistenceLayer(classNames), new WebLayer(classNames)};
        return serviceLayers;
    }
    public static void initModel(HashMap<String,String[]> modelMap,String layer){
        Iterator<Map.Entry<String,String[]>> it = modelMap.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String, String[]> entry = it.next();
            String key =entry.getKey();
            String[] values = entry.getValue();
            if(layer.equals("web")){
                generate(key, getWebLayer(values), values);
            }else if(layer.equals("service")){
                generate(key, getServiceLayers(values), values);
            }

        }


    }

}
