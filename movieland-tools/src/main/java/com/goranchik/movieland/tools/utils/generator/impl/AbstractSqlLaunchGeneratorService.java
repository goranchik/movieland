package com.goranchik.movieland.tools.utils.generator.impl;

import com.goranchik.movieland.tools.enums.Table;
import com.goranchik.movieland.tools.utils.generator.SqlLaunchGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import static com.goranchik.movieland.tools.Constants.*;

/**
 * Created by Ihor on 6/8/2016.
 */
public abstract class AbstractSqlLaunchGeneratorService implements SqlLaunchGeneratorService {

    @Autowired
    private String getDataPath;

   @Override
    public String getCreateTableSql(String tableName) {
        StringBuilder resultSQL = new StringBuilder();
        resultSQL.append(String.format(DROP_TABLE_SQL, tableName))
                .append(String.format(CREATE_TABLE_SQL, tableName,
                        Table.valueOf(tableName.toUpperCase()).getTableFields()))
                .append(END_LINE);
        return resultSQL.toString();
    }

    @Override
    public Resource getDataResource(String tableName) {
        return new ClassPathResource(getDataPath + tableName + TXT);
    }
}
