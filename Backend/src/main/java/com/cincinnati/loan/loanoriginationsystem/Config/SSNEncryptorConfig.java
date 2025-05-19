package com.cincinnati.loan.loanoriginationsystem.Config;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

@Converter
public class SSNEncryptorConfig implements AttributeConverter<String, String> {

    private final TextEncryptor textEncryptor;

    public SSNEncryptorConfig() {
        String FIELD_SECRET = System.getenv("FIELD_SECRET");
        String FIELD_SECRET_SALT = KeyGenerators.string().generateKey();

        if(FIELD_SECRET == null || FIELD_SECRET_SALT == null) {
            throw new IllegalStateException("Field secret");
        }

        this.textEncryptor = Encryptors.text(FIELD_SECRET, FIELD_SECRET_SALT);
    }

    @Override
    public String convertToDatabaseColumn(String plainText) {
        if(plainText == null) { return null;}
        return textEncryptor.encrypt(plainText);
    }

    @Override
    public String convertToEntityAttribute(String encryptedText) {
        if(encryptedText == null) { return null;}
        return textEncryptor.decrypt(encryptedText);
    }
}
