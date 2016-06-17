package com.goranchik.movieland.tools.utils.generator.impl;

import com.goranchik.movieland.tools.Table;
import com.goranchik.movieland.tools.utils.PropTools;
import com.goranchik.movieland.tools.utils.generator.SQLGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Properties;

import static com.goranchik.movieland.tools.Constants.*;

/**
 * Created by Ihor on 6/8/2016.
 */
public abstract class AbstractSQLGenerator implements SQLGenerator {

    @Autowired
    private String getDataPath;

    protected static Properties dbProperties = PropTools.getDBProperties();

    @Override
    public String getCreateTableSQL(String tableName) {
        StringBuilder resultSQL = new StringBuilder();
        resultSQL.append(String.format(DROP_TABLE_SQL, tableName))
                .append(String.format(CREATE_TABLE_SQL, tableName,
                        Table.valueOf(tableName.toUpperCase()).tableFields(dbProperties)))
                .append(END_LINE);
        return resultSQL.toString();
    }

    @Override
    public Resource getDataResource(String tableName) {
        return new ClassPathResource(getDataPath + tableName + TXT);
    }
}
