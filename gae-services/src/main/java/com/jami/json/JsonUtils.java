package com.jami.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author jonp
 */
public class JsonUtils {
    private static final ObjectMapper MAPPER = create();

    public static ObjectMapper create() {
        ObjectMapper m = new ObjectMapper();
        m.enable(SerializationFeature.INDENT_OUTPUT);
        m.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        m.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // So fields are returned no matter the annotation
        m.setAnnotationIntrospector(new JaxbAnnotationIntrospector());

        return m;
    }
    /**
     * Serializes object.
     *
     * @param value
     * @return
     * @throws JAXBException
     */
    public static String serialize(Object value) throws JAXBException {
        try {
            return MAPPER.writeValueAsString(value);
        } catch (IOException e) {
            throw new JAXBException("Error serializing java object.", e);
        }
    }

    /**
     * Deserializes a json String that represents list of objects
     * 
     * @param <T>
     * @param json
     * @param objectClass
     * @return
     * @throws IOException
     * @throws JAXBException
     */
    public static <T> List<T> deserializeToList(String json, Class<T> objectClass) throws JAXBException {
        try {
            return MAPPER.readValue(json, MAPPER.getTypeFactory().constructCollectionType(List.class, objectClass));
        } catch (IOException e) {
            throw new JAXBException("Error deserializing json string.", e);
        }
    }

    /**
     * Deserialize json string to object.
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     * @throws JAXBException
     */
    public static <T> T deserialize(String json, Class<T> clazz) throws JAXBException {
        return deserialize(json.getBytes(UTF_8), clazz);
    }

    /**
     * Deserialize json string to object.
     *
     * @param json
     * @param typeReference
     * @param <T>
     * @return
     * @throws JAXBException
     */
    public static <T> T deserialize(String json, TypeReference<T> typeReference) throws JAXBException {
        try {
            return MAPPER.readValue(json, typeReference);
        } catch (IOException e) {
            throw new JAXBException("Error de-serializing a json string.", e);
        }
    }

    /**
     * Deserialize json string to object.
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     * @throws JAXBException
     */
    private static <T> T deserialize(byte[] json, Class<T> clazz) throws JAXBException {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            throw new JAXBException("Error de-serializing a json string.", e);
        }
    }
}
