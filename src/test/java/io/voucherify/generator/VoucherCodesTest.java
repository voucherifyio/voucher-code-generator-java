package io.voucherify.generator;

import io.voucherify.generator.CodeConfig.Charset;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VoucherCodesTest {

    @Test
    public void shouldGenerateCodeOfGivenLength() {
        // given
        CodeConfig config = CodeConfig.length(10);
        
        // when
        String code = VoucherCodes.generate(config);
        
        // then
        assertThat(code.length()).isEqualTo(10);
    }
    
    @Test
    public void shouldGenerateNumericCode() {
        // given
        CodeConfig config = CodeConfig.length(8).withCharset(Charset.NUMBERS);
        
        // when
        String code = VoucherCodes.generate(config);
        
        // then
        assertThat(code).matches("^([0-9]){8}$");
    }
    
    @Test
    public void shouldGenerateCodeWithPrefix() {
        // given
        CodeConfig config = CodeConfig.length(8).withPrefix("TEST-");
        
        // when
        String code = VoucherCodes.generate(config);
        
        // then
        assertThat(code).startsWith("TEST-");
        assertThat(code.length()).isEqualTo(5 /*TEST-*/ + 8 /*random*/);
    }
    
    @Test
    public void shouldGenerateCodeWithPostfix() {
        // given
        CodeConfig config = CodeConfig.length(8).withPostfix("-TEST");
        
        // when
        String code = VoucherCodes.generate(config);
        
        // then
        assertThat(code).endsWith("-TEST");
        assertThat(code.length()).isEqualTo(8 /*random*/ + 5 /*-TEST*/);
    }
    
    @Test
    public void shouldGenerateCodeWithPrefixAndPostfix() {
        // given
        CodeConfig config = CodeConfig.length(8).withPrefix("TE-").withPostfix("-ST");
        
        // when
        String code = VoucherCodes.generate(config);
        
        // then
        assertThat(code).startsWith("TE-");
        assertThat(code).endsWith("-ST");
        assertThat(code.length()).isEqualTo(3 /*TE-*/ + 8 /*random*/ + 3 /*-ST*/);
    }
    
    @Test
    public void shouldGenerateCodeFromGivenPattern() {
        // given
        CodeConfig config = CodeConfig.pattern("##-###-##");
        
        // when
        String code = VoucherCodes.generate(config);
        
        // then
        assertThat(code).matches("^([0-9a-zA-Z]){2}-([0-9a-zA-Z]){3}-([0-9a-zA-Z]){2}$");
    }
}
