package com.kexin_do.tool.generator.tools.plugins;

import java.io.File;

public interface GeneratorPlugins {

    /**
     * 生成文件
     * @param taed 生成文件对应的实体类
     * @return 返回生成好的文件实例对象
     */
    StringBuilder generatorFile(TableAndEntityDetails taed) throws Exception;
}
