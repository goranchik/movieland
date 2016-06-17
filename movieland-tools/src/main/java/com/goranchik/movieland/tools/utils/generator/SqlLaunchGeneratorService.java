package com.goranchik.movieland.tools.utils.generator;

import org.springframework.core.io.Resource;

/**
 * Created by Ihor on 6/7/2016.
 */
public interface SqlLaunchGeneratorService {
    String getPopulateTableSql(String tableName);
    String getCreateTableSql(String tableName);
    Resource getDataResource(String tableName);
}
