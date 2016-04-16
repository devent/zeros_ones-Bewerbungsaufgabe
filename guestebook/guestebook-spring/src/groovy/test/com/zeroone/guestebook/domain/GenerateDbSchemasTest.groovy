package com.zeroone.guestebook.domain

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

import org.apache.commons.io.FileUtils
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

import com.zeroone.guestebook.domain.SchemaGenerator.Dialect


@Slf4j
@CompileStatic
class GenerateDbSchemasTest {

    @Test
    void "generate schemas"() {
        def generator = new SchemaGenerator('com.zeroone.guestebook.domain', Dialect.MYSQL)
        def dir = folder.newFolder()
        def dialect = Dialect.MYSQL
        def file = generator.generate(dir)
        assert file.isFile() == true
        log.info "Generated schema: ```{}'''", FileUtils.readFileToString(file)
    }

    @Rule
    public TemporaryFolder folder = new TemporaryFolder()
}
