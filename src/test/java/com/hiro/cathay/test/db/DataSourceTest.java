package com.hiro.cathay.test.db;

import com.hiro.cathay.test.CathayTestApplication;
import com.hiro.cathay.test.config.DataSourceContextHolder;
import com.hiro.cathay.test.config.DatabaseSource;
import com.hiro.cathay.test.dao.entity.Book;
import com.hiro.cathay.test.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CathayTestApplication.class)
public class DataSourceTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private TestService testService;

    @Before
    public void before() {
        DataSourceContextHolder.set(DatabaseSource.DB1);
        initSQL();
        testService.addBook("Book1", "DB1");
        DataSourceContextHolder.remove();

        DataSourceContextHolder.set(DatabaseSource.DB2);
        initSQL();
        testService.addBook("Book1", "DB2");
        DataSourceContextHolder.remove();

    }

    @Test
    public void testLoadProps() {
        List<Book> books1 = testService.getDB1Books();
        log.info("books1={}", books1);

        List<Book> books2 = testService.getDB2Books();
        log.info("books2={}", books2);
    }

    private void initSQL() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("schema.sql"));
        populator.execute(dataSource);
    }

}
