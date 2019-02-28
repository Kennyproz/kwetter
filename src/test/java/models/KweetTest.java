package models;

import org.junit.jupiter.api.BeforeEach;

import java.util.Date;

class KweetTest {

    @BeforeEach
    void setUp() {
        KweetConvertor kweetConvertor = new KweetConvertor();

        KweetCreator kweetCreator = new KweetCreator("Mijn naam is ken @kenny @ ken @kenz",new Date().toString(),"Kenny");

    }



}