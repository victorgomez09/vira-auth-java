package com.virasoftware.mailservice.kafka;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virasoftware.mailservice.dto.EmailVerificationDto;

public class EmailVerificationDtoDeserializer implements Deserializer<EmailVerificationDto> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public EmailVerificationDto deserialize(String s, byte[] data) {
        try {
            if (data == null){
                System.out.println("Null received at deserializing");
                return null;
            }
            
            System.out.println("Deserializing...");
            return objectMapper.readValue(new String(data, "UTF-8"), EmailVerificationDto.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to EmailVerificationDto");
        }
    }
}
