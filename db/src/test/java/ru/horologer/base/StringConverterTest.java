package ru.horologer.base;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringConverterTest {

    @Test
    void toObject() {
         assertEquals(1,StringConverter.toObject("1"));
         assertEquals(-1,StringConverter.toObject("-1"));
         assertEquals(Integer.MAX_VALUE,StringConverter.toObject("2147483647"));
         assertEquals(Integer.MIN_VALUE,StringConverter.toObject("-2147483648"));
         assertEquals(2147483648L,StringConverter.toObject("2147483648"));
         assertEquals(-2147483649L,StringConverter.toObject("-2147483649"));
         assertEquals(true, StringConverter.toObject("true"));
         assertNotEquals(false, StringConverter.toObject("true"));
         assertEquals(false, StringConverter.toObject("false"));
         assertNotEquals(true, StringConverter.toObject("false"));
         assertEquals("2-11",StringConverter.toObject("2-11"));
         assertEquals(StringConverter.toDate("22.12.2021"),StringConverter.toObject("22.12.2021"));
         assertEquals(StringConverter.toDate("22.12.2021 10:10:10"),StringConverter.toObject("22.12.2021  10:10:10"));
    }
}