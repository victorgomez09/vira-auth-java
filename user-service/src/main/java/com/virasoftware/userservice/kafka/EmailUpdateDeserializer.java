package com.virasoftware.userservice.kafka;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virasoftware.userservice.domains.dtos.EmailUpdateDto;
import java.nio.charset.StandardCharsets;

public class EmailUpdateDeserializer implements Deserializer<EmailUpdateDto> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public EmailUpdateDto deserialize(String s, byte[] data) {
        try {
            if (data == null) {
                System.out.println("Null received at deserializing");
                return null;
            }
            System.out.println("Deserializing... " + s);
            
            return objectMapper.readValue(new String(data, StandardCharsets.UTF_8), EmailUpdateDto.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to EmailUpdateDto");
        }
    }

}