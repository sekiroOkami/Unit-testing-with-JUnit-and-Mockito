package org.example;


import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import javax.swing.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;

public class MainFrameTest {
    private FrameFixture window = null;
    @BeforeEach
    void setUp() {
        var frame =  new MainFrame();
        window = new FrameFixture(frame.getFrame());
        window.show();
    }

    @AfterEach
    void tearDown() {
        window.cleanUp();
    }


    @Test
    void demoTextField() {
        window.textBox("jtextField1").setText("hello");
        window.textBox("jtextField1").requireText("hello");
    }


//    @ParameterizedTest
//    @CsvSource({"jlabel1, copiedText",
//            "jlabel2, label2"}
//    )
//    void shouldDisplayCorrectLabels(String labelName, String labelText) {
//        window.label(labelName).requireText(labelText);
//    }




}
