package io.voucherify.generator;

import java.security.SecureRandom;

public final class VoucherCodes {

    private static final SecureRandom RND = new SecureRandom();
    //preserve previous seed behavior (though not necessary, see https://docs.oracle.com/javase/8/docs/api/java/security/SecureRandom.html#SecureRandom--)
    RND.setSeed(System.currentTimeMillis());
       
    /**
     * Generates a random code according to given config. 
     * 
     * @param config
     * 
     * @return Generated code.
     */
    public static String generate(CodeConfig config) {
        StringBuilder sb = new StringBuilder();
        char[] chars = config.getCharset().toCharArray();
        char[] pattern = config.getPattern().toCharArray();
        
        if (config.getPrefix() != null) {
            sb.append(config.getPrefix());    
        }
        
        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i] == CodeConfig.PATTERN_PLACEHOLDER) {
                sb.append(chars[RND.nextInt(chars.length)]);
            } else {
                sb.append(pattern[i]);
            }
        }
        
        if (config.getPostfix() != null) {
            sb.append(config.getPostfix());    
        }
        
        return sb.toString();
    }
}
