package com.kexin_do.tool.generator.tools.plugins.dao.mybatis;

import com.kexin_do.tool.generator.tools.other.FileMaker;
import com.kexin_do.tool.generator.tools.plugins.GeneratorFiles;
import com.kexin_do.tool.generator.tools.plugins.GeneratorPlugins;
import com.kexin_do.tool.generator.tools.plugins.TableAndEntityDetails;

import javax.validation.constraints.NotNull;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Matcher;

/**
 * 生成mybatis风格的代码
 */
public class MybatisFilePlugins implements GeneratorPlugins {

    @Override
    public StringBuilder generatorFile(@NotNull TableAndEntityDetails taed) throws Exception {

        StringBuilder mybatisFileBuilder = new StringBuilder();
        String xmlFileDirStr = TableAndEntityDetails.filePrefix + taed.getMapperPath().replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + File.separator + taed.getEntityName() + "Mapper.java";
        File xmlFile = FileMaker.makeFile(xmlFileDirStr);

        try (FileWriter mybatisFileWriter = new FileWriter(xmlFile, false);
             BufferedWriter mybatisBufferedWriter = new BufferedWriter(mybatisFileWriter)) {

            String fileStr = MybatisTemplateConstant.makeMapperString(taed);
            mybatisBufferedWriter.write(fileStr);
            mybatisFileBuilder.append(fileStr);

        } catch (Exception e) {
            throw e;
        }

        return mybatisFileBuilder;
    }

}
