package com.zeroone.guestebook;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;

/**
 * Formats date field for guesbook entries. Uses the
 * {@code "guestbook.date.format"} entry from the {@code messages.properties}.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
public class DateFormatter implements Formatter<Date> {

    private final MessageSource messageSource;

    public DateFormatter(MessageSource messageSource) {
        super();
        this.messageSource = messageSource;
    }

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        SimpleDateFormat dateFormat = createDateFormat(locale);
        return dateFormat.parse(text);
    }

    @Override
    public String print(Date object, Locale locale) {
        SimpleDateFormat dateFormat = createDateFormat(locale);
        return dateFormat.format(object);
    }

    private SimpleDateFormat createDateFormat(Locale locale) {
        String format;
        format = messageSource
                .getMessage("guestbook.date.format", null, locale);
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false);
        return dateFormat;
    }

}
