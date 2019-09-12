package com.kexin_do.tool.generator.tools.plugins.entity;

import com.kexin_do.tool.generator.tools.other.FileMaker;
import com.kexin_do.tool.generator.tools.plugins.GeneratorPlugins;
import com.kexin_do.tool.generator.tools.plugins.TableAndEntityDetails;
import com.kexin_do.tool.generator.tools.plugins.dao.mybatis.MybatisTemplateConstant;

import javax.validation.constraints.NotNull;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.regex.Matcher;

public class EntityFilePlugin implements GeneratorPlugins {
    @Override
    public StringBuilder generatorFile(@NotNull TableAndEntityDetails taed) throws Exception {

        StringBuilder entityFileBuilder = new StringBuilder();
        String entityFileDirStr = TableAndEntityDetails.filePrefix + taed.getEntityPath().replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + File.separator + taed.getEntityName() + ".java";
        File entityFile = FileMaker.makeFile(entityFileDirStr);

        try (FileWriter mybatisFileWriter = new FileWriter(entityFile, false);
             BufferedWriter mybatisBufferedWriter = new BufferedWriter(mybatisFileWriter)) {

            String fileStr = MybatisTemplateConstant.makeMapperString(taed);
            mybatisBufferedWriter.write(fileStr);
            entityFileBuilder.append(fileStr);

        } catch (Exception e) {
            throw e;
        }

        return entityFileBuilder;
    }
}
