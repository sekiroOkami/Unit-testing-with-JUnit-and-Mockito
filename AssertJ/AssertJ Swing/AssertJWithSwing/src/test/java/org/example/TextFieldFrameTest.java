package org.example;

import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.swing.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TextFieldFrameTest {
    private FrameFixture window = null;
    @BeforeEach
    void setUp() {
        var frame = new TextFieldFrame();
        window = new FrameFixture(frame.getFrame());
        window.show();
    }

    @AfterEach
    void tearDown() {
        window.cleanUp();
    }

    @Test
    void demo() {
        JTextComponentFixture textField = window.textBox("textField");
        textField.setText("hi");
        assertAll(
                ()-> assertThat(textField).as("Test JTextField").isNotNull(),
                ()-> assertThat(textField.text()).isEqualTo("Sample")
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"textField"})
    void shouldCheckJTextFieldComponentIsExist(String textFieldName) {
        JTextComponentFixture textfield = window.textBox(textFieldName);
        textfield.setText("Sample");
        assertAll(
                ()-> assertThat(textfield).as("Test JTextField").isNotNull(),
                ()-> assertThat(textfield.text()).isEqualTo("Sample")
        );
    }

}