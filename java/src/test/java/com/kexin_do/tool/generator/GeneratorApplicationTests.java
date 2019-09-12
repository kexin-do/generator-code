package com.kexin_do.tool.generator;

import com.kexin_do.tool.generator.entity.Column;
import com.kexin_do.tool.generator.entity.Table;
import com.kexin_do.tool.generator.repository.TableRepository;
import com.kexin_do.tool.generator.tools.plugins.GeneratorPlugins;
import com.kexin_do.tool.generator.tools.plugins.TableAndEntityDetails;
import com.kexin_do.tool.generator.tools.plugins.TableAndEntityDetailsFactory;
import com.kexin_do.tool.generator.tools.plugins.dao.mybatis.MybatisFilePlugins;
import com.kexin_do.tool.generator.tools.plugins.dao.mybatis.MybatisXmlFilePlugins;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeneratorApplicationTests {

    @Autowired
    private TableRepository tableRepository;

    @Test
    public void contextLoads() {
        List<Column> rbac_user = tableRepository.getTableDetail("RBAC_USER");
        System.out.println(rbac_user);

        List<String> tableNames = tableRepository.getTableNames(0, 10);
        System.out.println(tableNames);

        Table table = new Table();
        table.setColumn(rbac_user);
        table.setTableName("RBAC_USER");
        try {
            TableAndEntityDetails instance = TableAndEntityDetailsFactory.getInstance("", "", "",
                    "", "", table);
            GeneratorPlugins mybatisPlugins = new MybatisFilePlugins();
            StringBuilder stringBuilder = mybatisPlugins.generatorFile(instance);
            System.out.println(stringBuilder);
            GeneratorPlugins mybatisXmlPlugins = new MybatisXmlFilePlugins();
            StringBuilder stringBuilder1 = mybatisXmlPlugins.generatorFile(instance);
            System.out.println(stringBuilder1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
