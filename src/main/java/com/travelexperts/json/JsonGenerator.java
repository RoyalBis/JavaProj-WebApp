package com.travelexperts.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import java.lang.reflect.Type;

/**
 * Class for Generating JSON
 * Created by Royal Bissell on 4/9/2016.
 */
public class JsonGenerator
{
    private static Gson gson = new Gson();

    public static String generateJson(Object to)
    {
        return null == to ? "" : gson.toJson(to);
    }

    public static String generateJson(Object to, TypeToken typeToken)
    {
        return null == to ? "" : gson.toJson(to, typeToken.getType());
    }

    public static Object generateTofromJson(String json, Class<?> clazz)
    {
        return gson.fromJson(json, clazz);
    }

    public static String generateErrorJson(Exception exception)
    {
        JsonError errorTO = new JsonError();
        errorTO.setCode(-1);
        errorTO.setMessage(exception.getMessage());
        return generateJson(errorTO);
    }

    public static String generateSuccessJson(String message)
    {
        JsonSuccess successTO =  new JsonSuccess();
        successTO.setCode(0);
        successTO.setMessage(message);
        return generateJson(successTO);
    }
}
