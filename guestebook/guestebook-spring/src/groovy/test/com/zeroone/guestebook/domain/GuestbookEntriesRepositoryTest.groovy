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
package com.zeroone.guestebook.domain;

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import javax.transaction.Transactional

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import com.zeroone.guestebook.Application


/**
 * {@link GuestbookEntriesRepository}
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
class GuestbookEntriesRepositoryTest {

    @Autowired
    GuestbookEntriesRepository repository

    @Test
    void "persists guestbook entry"() {
        def entry = new GuestbookEntry("A", "Comment A");
        repository.save(entry);
        assertThat(repository.findAll(), hasItem(entry));
    }
}
