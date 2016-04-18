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
$(document).ready(function() {
	'use strict';

	$('#guestbook_first_button').click(function(e) {
		e.preventDefault();
		$.ajax({
			type	: 'POST',
			cache	: false,
			url		: '/guestbook',
			data	: { pageNumber: 0 },
			success	: function(data) {
				$("#guestbook").html('<div>' + data + '</div>');
			}
		});
	});

	$('#guestbook_prev_button').click(function(e) {
		e.preventDefault();
		var tag = this;
		var number = tag.getAttribute("href").split('/')[2];
		$.ajax({
			type	: 'POST',
			cache	: false,
			url		: '/guestbook',
			data	: { pageNumber: number },
			success	: function(data) {
				$("#guestbook").html('<div>' + data + '</div>');
			}
		});
	});

	$('#guestbook_next_button').click(function(e) {
		e.preventDefault();
		var tag = this;
		var number = tag.getAttribute("href").split('/')[2];
		$.ajax({
			type	: 'POST',
			cache	: false,
			url		: '/guestbook',
			data	: { pageNumber: number },
			success	: function(data) {
				$("#guestbook").html('<div>' + data + '</div>');
			}
		});
	});

	$('#guestbook_last_button').click(function(e) {
		e.preventDefault();
		var tag = this;
		var number = tag.getAttribute("href").split('/')[2];
		$.ajax({
			type	: 'POST',
			cache	: false,
			url		: '/guestbook',
			data	: { pageNumber: number },
			success	: function(data) {
				$("#guestbook").html('<div>' + data + '</div>');
			}
		});
	});

});
