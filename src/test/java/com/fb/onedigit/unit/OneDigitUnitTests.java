package com.fb.onedigit.unit;

import com.fb.onedigit.service.OneDigitService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayName("Digito único - Unitários")
public class OneDigitUnitTests {

    @Test
    @DisplayName("Deve calcular digito único")
    public void shouldCalculateOneDigit() {
        OneDigitService mock = Mockito.mock(OneDigitService.class);
        Mockito.when(mock.findOneDigit("23")).thenReturn(5);
    }

    @Test
    @DisplayName("Deve Falhar ao calcular digito único")
    public void shouldFailCalculateOneDigit() {
        OneDigitService mock = Mockito.mock(OneDigitService.class);
        Mockito.when(mock.findOneDigit("abc")).thenThrow(NumberFormatException.class);
    }
}
