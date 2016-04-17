/*
 * Copyright 2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
