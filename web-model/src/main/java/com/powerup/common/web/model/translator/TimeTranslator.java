package com.powerup.common.web.model.translator;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The <code>TimeTranslator</code> class is a translator that will convert different time representations between the domain model and web model
 * representations.
 *
 * @author Chris Picard
 */
@SuppressWarnings("unused")
public final class TimeTranslator {
    /**
     * The default timezone used if no timezone is provided.
     */
    public static final String DEFAULT_TIMEZONE = "UTC";
    private static final Logger logger = LoggerFactory.getLogger(TimeTranslator.class);

    private TimeTranslator() {
    }

    /**
     * Translates from the xml date time definition to the Java Zoned Date Time representation.
     *
     * @param xmlGregorianCalendar XML Date Time representation to be translated.
     *
     * @return Java Zoned Date Time representation of the date time.
     */
    public static ZonedDateTime zonedDateTimeFromXml(final XMLGregorianCalendar xmlGregorianCalendar) {
        if (xmlGregorianCalendar != null) {
            return xmlGregorianCalendar.toGregorianCalendar().toZonedDateTime();
        }
        return null;
    }

    /**
     * Translates from Java Date Time into XML Time representation.
     *
     * @param zonedDateTime Java Date Time to be translated.
     *
     * @return XML representation of Date Time.
     */
    public static XMLGregorianCalendar zonedDateTimeToXml(final ZonedDateTime zonedDateTime) {
        if (zonedDateTime != null) {
            GregorianCalendar gregorianCalendar = GregorianCalendar.from(zonedDateTime);
            try {
                return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
            } catch (DatatypeConfigurationException ex) {
                logger.error("Unable to convert zoned date time", ex);
            }
        }
        return null;
    }

    /**
     * Translates from the xml date time definition to the Java Local Date Time representation.
     *
     * @param xmlGregorianCalendar XML Date Time representation to be translated.
     *
     * @return Java Local Date Time representation of the date time.
     */
    public static LocalDateTime localDateTimeFromXml(final XMLGregorianCalendar xmlGregorianCalendar) {
        if (xmlGregorianCalendar != null) {
            return xmlGregorianCalendar.toGregorianCalendar().toZonedDateTime().toInstant().atZone(ZoneId.of(DEFAULT_TIMEZONE)).toLocalDateTime();
        }
        return null;
    }

    /**
     * Translates from Java Date Time into XML Time representation.
     *
     * @param localDateTime Java Date Time to be translated.
     *
     * @return XML representation of Date Time.
     */
    public static XMLGregorianCalendar localDateTimeToXml(final LocalDateTime localDateTime) {
        if (localDateTime != null) {
            GregorianCalendar gregorianCalendar = GregorianCalendar.from(ZonedDateTime.of(localDateTime, ZoneId.of(DEFAULT_TIMEZONE)));
            try {
                return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
            } catch (DatatypeConfigurationException ex) {
                logger.error("Unable to convert local date time", ex);
            }
        }
        return null;
    }

    /**
     * Translates from the xml date time definition to the Java Local Date representation.
     *
     * @param xmlGregorianCalendar XML Date Time representation to be translated.
     *
     * @return Java Local Date representation of the date time.
     */
    public static LocalDate localDateFromXml(final XMLGregorianCalendar xmlGregorianCalendar) {
        if (xmlGregorianCalendar != null) {
            return xmlGregorianCalendar.toGregorianCalendar().toZonedDateTime().toInstant().atZone(ZoneId.of(DEFAULT_TIMEZONE)).toLocalDate();
        }
        return null;
    }

    /**
     * Translates from Time model object into XML Time representation.
     *
     * @param localDate Time to be translated.
     *
     * @return XML representation of Time.
     */
    public static XMLGregorianCalendar localDateToXml(final LocalDate localDate) {
        if (localDate != null) {
            GregorianCalendar gregorianCalendar = GregorianCalendar.from(ZonedDateTime.of(localDate, LocalTime.MIDNIGHT,
                    ZoneId.of(DEFAULT_TIMEZONE)));
            try {
                return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
            } catch (DatatypeConfigurationException ex) {
                logger.error("Unable to convert local date", ex);
            }
        }
        return null;
    }

    /**
     * Translates from the xml date time definition to the Java Local time representation.
     *
     * @param xmlGregorianCalendar XML Date Time representation to be translated.
     *
     * @return Java Local time representation of the date time.
     */
    public static LocalTime localTimeFromXml(final XMLGregorianCalendar xmlGregorianCalendar) {
        if (xmlGregorianCalendar != null) {
            return xmlGregorianCalendar.toGregorianCalendar().toZonedDateTime().toInstant().atZone(ZoneId.of(DEFAULT_TIMEZONE)).toLocalTime();
        }
        return null;
    }

    /**
     * Translates from Time model object into XML Time representation.
     *
     * @param localTime Time to be translated.
     *
     * @return XML representation of Time.
     */
    public static XMLGregorianCalendar localTimeToXml(final LocalTime localTime) {
        if (localTime != null) {
            GregorianCalendar gregorianCalendar = GregorianCalendar.from(ZonedDateTime.of(LocalDate.now(), localTime, ZoneId.of(DEFAULT_TIMEZONE)));
            try {
                return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
            } catch (DatatypeConfigurationException ex) {
                logger.error("Unable to convert local time", ex);
            }
        }
        return null;
    }

    /**
     * Translates from the xml duration definition to the Java Duration representation.
     *
     * @param jaxbDuration XML Duration representation to be translated.
     *
     * @return Java Duration representation of the duration.
     */
    public static Duration durationFromXml(final javax.xml.datatype.Duration jaxbDuration) {
        if (jaxbDuration == null) {
            return null;
        }
        return Duration.parse(jaxbDuration.toString());
    }

    /**
     * Translates from Java Duration model object into XML Duration representation.
     *
     * @param duration Duration to be translated.
     *
     * @return XML representation of Duration.
     */
    public static javax.xml.datatype.Duration durationToXml(final Duration duration) {
        if (duration != null) {
            try {
                return DatatypeFactory.newInstance().newDuration(duration.toString());
            } catch (DatatypeConfigurationException ex) {
                logger.error("Unable to convert duration", ex);
            }
        }
        return null;
    }

    /**
     * Translates from the xml month definition to the Java Month representation.
     *
     * @param month XML Month representation to be translated.
     *
     * @return Java Month representation of the month.
     */
    public static Month monthFromXml(final String month) {
        if (StringUtils.isNotBlank(month)) {
            return Month.valueOf(month.toUpperCase());
        } else {
            return null;
        }
    }

    /**
     * Translates from Java Month model object into XML Month representation.
     *
     * @param month Month to be translated.
     *
     * @return XML representation of Month.
     */
    public static String monthToXml(final Month month) {
        if (month != null) {
            return StringUtils.capitalize(month.toString().toLowerCase());
        } else {
            return null;
        }
    }
}