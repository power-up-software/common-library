package com.powerup.common.web.model.translator;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings("AccessStaticViaInstance")
@edu.umd.cs.findbugs.annotations.SuppressWarnings("RCN_REDUNDANT_NULLCHECK_WOULD_HAVE_BEEN_A_NPE")
public class TimeTranslatorTest {
    private static final ZonedDateTime TIME = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of(TimeTranslator.DEFAULT_TIMEZONE));
    private static final Duration DURATION = Duration.ofMinutes(7);
    @Mock
    private XMLGregorianCalendar mockXmlGregorianCalendar;
    @Mock
    private GregorianCalendar mockGregorianCalendar;
    @Mock
    private DatatypeFactory mockDateTypeFactory;
    @Mock
    private Logger mockLogger;
    @Mock
    private javax.xml.datatype.Duration mockDuration;

    @InjectMocks
    private TimeTranslator instance;

    @Test
    public void testLocalDateTimeFromXml_NominalCase() {
        when(mockXmlGregorianCalendar.toGregorianCalendar()).thenReturn(mockGregorianCalendar);
        when(mockGregorianCalendar.toZonedDateTime()).thenReturn(TIME);

        LocalDateTime result = instance.localDateTimeFromXml(mockXmlGregorianCalendar);

        assertEquals(TIME.toInstant(), result.toInstant(ZoneOffset.UTC));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testLocalDateTimeFromXml_NullInput() {
        LocalDateTime result = instance.localDateTimeFromXml(null);
        assertNull(result);
    }

    @Test
    public void testLocalDateTimeToXml_NominalCase() {
        try (MockedStatic<DatatypeFactory> staticMockDatatypeFactoryMockedStatic = Mockito.mockStatic(DatatypeFactory.class)) {
            try (MockedStatic<GregorianCalendar> staticMockGregorianCalendar = Mockito.mockStatic(GregorianCalendar.class)) {
                staticMockGregorianCalendar.when(() -> GregorianCalendar.from(ZonedDateTime.of(TIME.toLocalDate(), TIME.toLocalTime(),
                        ZoneId.of(TimeTranslator.DEFAULT_TIMEZONE)))).thenReturn(mockGregorianCalendar);
                staticMockDatatypeFactoryMockedStatic.when(DatatypeFactory::newInstance).thenReturn(mockDateTypeFactory);
                instance.localDateTimeToXml(TIME.toLocalDateTime());
                staticMockGregorianCalendar.verify(() -> GregorianCalendar.from(ZonedDateTime.of(TIME.toLocalDate(), TIME.toLocalTime(),
                        ZoneId.of(TimeTranslator.DEFAULT_TIMEZONE))), times(1));
                verify(mockDateTypeFactory, times(1)).newXMLGregorianCalendar(mockGregorianCalendar);
            }
        }
    }

    @Test
    public void testLocalDateTimeToXmlError() {
        try (MockedStatic<DatatypeFactory> staticMockDateTypeFactory = Mockito.mockStatic(DatatypeFactory.class)) {
            staticMockDateTypeFactory.when(DatatypeFactory::newInstance).thenThrow(new DatatypeConfigurationException());
            XMLGregorianCalendar result = TimeTranslator.localDateTimeToXml(TIME.toLocalDateTime());
            assertNull(result);
            verify(mockLogger, times(1)).error(anyString(), any(Throwable.class));
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testLocalDateTimeToXml_NullInput() {
        XMLGregorianCalendar result = TimeTranslator.localDateTimeToXml(null);
        assertNull(result);
    }

    @Test
    public void testLocalDateFromXml_NominalCase() {
        when(mockXmlGregorianCalendar.toGregorianCalendar()).thenReturn(mockGregorianCalendar);
        when(mockGregorianCalendar.toZonedDateTime()).thenReturn(TIME);
        LocalDate result = TimeTranslator.localDateFromXml(mockXmlGregorianCalendar);
        assertEquals(TIME.toLocalDate(), result);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testLocalDateFromXml_NullInput() {
        LocalDate result = TimeTranslator.localDateFromXml(null);
        assertNull(result);
    }

    @Test
    public void testLocalDateToXml_NominalCase() {
        try (MockedStatic<DatatypeFactory> staticMockDatatypeFactoryMockedStatic = Mockito.mockStatic(DatatypeFactory.class)) {
            try (MockedStatic<GregorianCalendar> staticMockGregorianCalendar = Mockito.mockStatic(GregorianCalendar.class)) {
                staticMockGregorianCalendar.when(() -> GregorianCalendar.from(ZonedDateTime.of(TIME.toLocalDate(), LocalTime.MIDNIGHT,
                        ZoneId.of(TimeTranslator.DEFAULT_TIMEZONE)))).thenReturn(mockGregorianCalendar);
                staticMockDatatypeFactoryMockedStatic.when(DatatypeFactory::newInstance).thenReturn(mockDateTypeFactory);
                instance.localDateToXml(TIME.toLocalDate());
                staticMockGregorianCalendar.verify(() -> GregorianCalendar.from(ZonedDateTime.of(TIME.toLocalDate(), LocalTime.MIDNIGHT,
                        ZoneId.of(TimeTranslator.DEFAULT_TIMEZONE))), times(1));
                verify(mockDateTypeFactory, times(1)).newXMLGregorianCalendar(mockGregorianCalendar);
            }
        }
    }

    @Test
    public void testLocalDateToXml_Error() {
        try (MockedStatic<DatatypeFactory> staticMockDateTypeFactory = Mockito.mockStatic(DatatypeFactory.class)) {
            staticMockDateTypeFactory.when(DatatypeFactory::newInstance).thenThrow(new DatatypeConfigurationException());
            XMLGregorianCalendar result = TimeTranslator.localDateToXml(TIME.toLocalDate());
            assertNull(result);
            verify(mockLogger, times(1)).error(anyString(), any(Throwable.class));
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testLocalDateToXml_NullInput() {
        XMLGregorianCalendar result = TimeTranslator.localDateToXml(null);
        assertNull(result);
    }

    @Test
    public void testLocalTimeFromXml_NominalCase() {
        when(mockXmlGregorianCalendar.toGregorianCalendar()).thenReturn(mockGregorianCalendar);
        when(mockGregorianCalendar.toZonedDateTime()).thenReturn(TIME);

        LocalTime result = TimeTranslator.localTimeFromXml(mockXmlGregorianCalendar);

        assertEquals(TIME.toLocalTime(), result);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testLocalTimeFromXml_NullInput() {
        LocalTime result = TimeTranslator.localTimeFromXml(null);
        assertNull(result);
    }

    @Test
    public void testLocalTimeToXml_NominalCase() {
        try (MockedStatic<DatatypeFactory> staticMockDatatypeFactoryMockedStatic = Mockito.mockStatic(DatatypeFactory.class)) {
            try (MockedStatic<GregorianCalendar> staticMockGregorianCalendar = Mockito.mockStatic(GregorianCalendar.class)) {
                staticMockGregorianCalendar.when(() -> GregorianCalendar.from(ZonedDateTime.of(TIME.toLocalDate(), TIME.toLocalTime(),
                        ZoneId.of(TimeTranslator.DEFAULT_TIMEZONE)))).thenReturn(mockGregorianCalendar);
                staticMockDatatypeFactoryMockedStatic.when(DatatypeFactory::newInstance).thenReturn(mockDateTypeFactory);
                instance.localTimeToXml(TIME.toLocalTime());
                staticMockGregorianCalendar.verify(() -> GregorianCalendar.from(ZonedDateTime.of(TIME.toLocalDate(), TIME.toLocalTime(),
                        ZoneId.of(TimeTranslator.DEFAULT_TIMEZONE))), times(1));
                verify(mockDateTypeFactory, times(1)).newXMLGregorianCalendar(mockGregorianCalendar);
            }
        }
    }

    @Test
    public void testLocalTimeToXml_Error() {
        try (MockedStatic<DatatypeFactory> staticMockDateTypeFactory = Mockito.mockStatic(DatatypeFactory.class)) {
            staticMockDateTypeFactory.when(DatatypeFactory::newInstance).thenThrow(new DatatypeConfigurationException());
            XMLGregorianCalendar result = TimeTranslator.localTimeToXml(TIME.toLocalTime());
            assertNull(result);
            verify(mockLogger, times(1)).error(anyString(), any(Throwable.class));
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testLocalTimeToXml_NullInput() {
        XMLGregorianCalendar result = TimeTranslator.localTimeToXml(null);
        assertNull(result);
    }

    @Test
    public void testDurationFromXml_NominalCase() {
        when(mockDuration.toString()).thenReturn("PT7M");

        Duration result = TimeTranslator.durationFromXml(mockDuration);

        assertEquals(DURATION.toMillis(), result.toMillis());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testDurationFromXml_NullInput() {
        Duration result = TimeTranslator.durationFromXml(null);
        assertNull(result);
    }

    @Test
    public void testDurationToXml() {
        try (MockedStatic<DatatypeFactory> staticMockDatatypeFactoryMockedStatic = Mockito.mockStatic(DatatypeFactory.class)) {
            staticMockDatatypeFactoryMockedStatic.when(DatatypeFactory::newInstance).thenReturn(mockDateTypeFactory);
            when(mockDateTypeFactory.newDuration(DURATION.toString())).thenReturn(mockDuration);
            javax.xml.datatype.Duration result = instance.durationToXml(DURATION);
            assertEquals(mockDuration, result);
        }
    }

    @Test
    public void testDurationToXml_Error() {
        try (MockedStatic<DatatypeFactory> staticMockDateTypeFactory = Mockito.mockStatic(DatatypeFactory.class)) {
            staticMockDateTypeFactory.when(DatatypeFactory::newInstance).thenThrow(new DatatypeConfigurationException());
            javax.xml.datatype.Duration result = TimeTranslator.durationToXml(DURATION);
            assertNull(result);
            verify(mockLogger, times(1)).error(anyString(), any(Throwable.class));
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testDurationToXml_NullInput() {
        javax.xml.datatype.Duration result = TimeTranslator.durationToXml(null);
        assertNull(result);
    }

    @Test
    public void tesMonthFromXml_NominalCase() {
        Month result = TimeTranslator.monthFromXml("April");

        assertEquals(Month.APRIL, result);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testMonthFromXml_NullInput() {
        Month result = TimeTranslator.monthFromXml(null);
        assertNull(result);
    }

    @Test
    public void testMonthToXml() {
        String result = instance.monthToXml(Month.JANUARY);
        assertEquals("January", result);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testMonthToXml_NullInput() {
        String result = TimeTranslator.monthToXml(null);
        assertNull(result);
    }

}