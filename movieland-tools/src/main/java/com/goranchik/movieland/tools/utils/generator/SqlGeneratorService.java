package com.goranchik.movieland.tools.utils.generator;

import com.goranchik.movieland.tools.dto.MovieSearchDto;
import com.goranchik.movieland.tools.dto.MovieSortDto;

/**
 * Created by Ihor on 6/17/2016.
 */
public interface SqlGeneratorService {
    String getSearchSql(MovieSearchDto searchDto);
    String getSortSql(MovieSortDto sortDto);
    String getSortSqlBatch(MovieSortDto sortDto);
}
