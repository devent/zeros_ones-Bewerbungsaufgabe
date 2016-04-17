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

	$('#form').submit(function(e) {

		if(!$('#use_ajax').is(':checked')) {
			return;
		}

		e.preventDefault();

		var form = $(this);

		$.ajax({
			type	: 'POST',
			cache	: false,
			url		: form.attr('action'),
			data	: form.serialize(),
			success	: function(data) {
				$("#entries").append('<div>' + data + '</div>');

				// fix index
				var index = $('#entries div[id^="entry"]').length;
				var textArray = $(data).find('h3').text().split('.', 2);

				$('#entries div[id^="entry"]:last').find('h3').text(index + '.' + textArray[1]);
				$('html, body').animate({scrollTop: form.offset().top}, 2000);

				e.target.reset();
			}
		});
	});

	$('#entries').on('submit','form', function(e){

		if(!$('#use_ajax').is(':checked')) {
			return;
		}

		e.preventDefault();

		var form = $(this);
		var id = form.attr('data-entry-id');

		$.ajax({
			type	: 'DELETE',
			cache	: false,
			url		: form.attr('action'),
			data	: form.serialize(),
			success	: function() {

				$('#entry' + id).slideUp(500, function() {
					var followingEntries = $(this).parent().nextAll().each(function() {
						var textArray = $(this).find('h3').text().split('.', 2);
						$(this).find('h3').text((parseInt(textArray[0],10)-1) + '.' + textArray[1]);
					});

					$(this).parent().remove();
				});
			}
		});
	});
});
