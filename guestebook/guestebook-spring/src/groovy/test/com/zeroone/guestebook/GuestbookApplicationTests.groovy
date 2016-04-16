package com.zeroone.guestebook;

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
class GuestbookApplicationTests {

    @Autowired
    JdbcTemplate template

    @Test
    void testDefaultSettings() {
        assert 0 == template.queryForObject("SELECT COUNT(*) from GuestbookEntry", Integer.class)
    }
}
