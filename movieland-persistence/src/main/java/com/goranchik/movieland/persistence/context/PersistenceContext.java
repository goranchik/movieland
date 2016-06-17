package com.goranchik.movieland.persistence.context;

import com.goranchik.movieland.tools.context.ToolsContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by Ihor on 6/8/2016.
 */
@Configuration
@ComponentScan({
        "com.goranchik.movieland.persistence.dao",
        "com.goranchik.movieland.persistence.utils"
})
@Import(ToolsContext.class)
@PropertySource("classpath:config/jdbc.properties")
public class PersistenceContext {

    @Value("${jdbc.driver.classname}")
    private String jdbcDriverClassName;

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.username}")
    private String jdbcUserName;

    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Value("${jdbc.data.path}")
    private String jdbcDataPath;

    @Value("${jdbc.sql.find.genre.by.id}")
    private String jdbcFindGenreByIdSQL;

    @Value("${jdbc.sql.find.all.genres}")
    private String jdbcFindAllGenresSQL;

    @Value("${jdbc.sql.find.country.by.id}")
    private String jdbcFindCountryByIdSQL;

    @Value("${jdbc.sql.find.all.countries}")
    private String jdbcFindAllCountriesSQL;

    @Value("${jdbc.sql.find.movie.by.id}")
    private String jdbcFindMovieByIdSQL;

    @Value("${jdbc.sql.find.all.movies}")
    private String jdbcFindAllMoviesSQL;

    @Value("${jdbc.sql.find.genres.by.movie.id}")
    private String jdbcFindGenresByMovieIdSQL;

    @Value("${jdbc.sql.find.countries.by.movie.id}")
    private String jdbcFindCountriesByMovieIdSQL;

    @Value("${jdbc.sql.find.user.by.id}")
    private String jdbcFindUserByIdSQL;

    @Value("${jdbc.sql.find.all.users}")
    private String jdbcFindAllUsersSQL;

    @Value("${jdbc.sql.find.all.reviews}")
    private String jdbcFindAllReviewsSQL;

    @Value("${jdbc.sql.find.reviews.by.movie.id}")
    private String jdbcFindReviewsByMovieIdSQL;

    @Value("${jdbc.sql.find.movies.by.genre.predicate}")
    private String jdbcFindMoviesByGenrePredicate;

    @Value("${jdbc.sql.find.movies.by.title.predicate}")
    private String jdbcFindMoviesByTitlePredicate;

    @Value("${jdbc.sql.find.movies.by.year.predicate}")
    private String jdbcFindMoviesByYearPredicate;

    @Value("${jdbc.sql.find.movies.by.country.predicate}")
    private String jdbcFindMoviesByCountryPredicate;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(jdbcDriverClassName);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUserName);
        dataSource.setPassword(jdbcPassword);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public String getDataPath() {
        return jdbcDataPath;
    }

    @Bean
    public String findGenreByIdSQL() {
        return jdbcFindGenreByIdSQL;
    }

    @Bean
    public String findAllGenresSQL() {
        return jdbcFindAllGenresSQL;
    }

    @Bean
    public String findCountryByIdSQL() {
        return jdbcFindCountryByIdSQL;
    }

    @Bean
    public String findAllCountriesSQL() {
        return jdbcFindAllCountriesSQL;
    }

    @Bean
    public String findMovieByIdSQL() {
        return jdbcFindMovieByIdSQL;
    }

    @Bean
    public String findAllMoviesSQL() {
        return jdbcFindAllMoviesSQL;
    }

    @Bean
    public String findGenresByMovieIdSQL() {
        return jdbcFindGenresByMovieIdSQL;
    }

    @Bean
    public String findCountriesByMovieIdSQL() {
        return jdbcFindCountriesByMovieIdSQL;
    }

    @Bean
    public String findUserByIdSQL() {
        return jdbcFindUserByIdSQL;
    }

    @Bean
    public String findAllUsersSQL() {
        return jdbcFindAllUsersSQL;
    }

    @Bean
    public String findAllReviewsSQL() {
        return jdbcFindAllReviewsSQL;
    }

    @Bean
    public String findReviewsByMovieIdSQL() {
        return jdbcFindReviewsByMovieIdSQL;
    }

    @Bean
    public String findMoviesByGenrePredicate() {
        return jdbcFindMoviesByGenrePredicate;
    }

    @Bean
    public String findMoviesByTitlePredicate() {
        return jdbcFindMoviesByTitlePredicate;
    }

    @Bean
    public String findMoviesByYearPredicate() {
        return jdbcFindMoviesByYearPredicate;
    }

    @Bean
    public String findMoviesByCountryPredicate() {
        return jdbcFindMoviesByCountryPredicate;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
