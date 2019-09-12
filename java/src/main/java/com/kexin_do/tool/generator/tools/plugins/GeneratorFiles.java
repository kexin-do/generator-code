package com.kexin_do.tool.generator.tools.plugins;

import java.io.File;
import java.util.List;

/**
 * 一个表对应生成的多个文件
 */
public class GeneratorFiles {

    /**
     * xml,mybatis的sql语句
     */
    private List<File> xmlFile;

    /**
     * 数据访问层文件
     */
    private List<File> daoFile;

    /**
     * 服务层文件
     */
    private List<File> serviceFile;

    /**
     * 视图层
     */
    private List<File> controllerFile;

    /**
     * vue页面文件
     */
    private List<File> vueFile;

    /**
     * js文件
     */
    private List<File> jsFile;

    public List<File> getXmlFile() {
        return xmlFile;
    }

    public void setXmlFile(List<File> xmlFile) {
        this.xmlFile = xmlFile;
    }

    public List<File> getDaoFile() {
        return daoFile;
    }

    public void setDaoFile(List<File> daoFile) {
        this.daoFile = daoFile;
    }

    public List<File> getServiceFile() {
        return serviceFile;
    }

    public void setServiceFile(List<File> serviceFile) {
        this.serviceFile = serviceFile;
    }

    public List<File> getControllerFile() {
        return controllerFile;
    }

    public void setControllerFile(List<File> controllerFile) {
        this.controllerFile = controllerFile;
    }

    public List<File> getVueFile() {
        return vueFile;
    }

    public void setVueFile(List<File> vueFile) {
        this.vueFile = vueFile;
    }

    public List<File> getJsFile() {
        return jsFile;
    }

    public void setJsFile(List<File> jsFile) {
        this.jsFile = jsFile;
    }
}
