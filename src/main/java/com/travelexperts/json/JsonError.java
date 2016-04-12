package com.travelexperts.json;

/**
 * Created by 723403 on 4/9/2016.
 */
public class JsonError implements JsonResult
{
    int code;
    String message;

    @Override
    public int getCode()
    {
        return code;
    }

    @Override
    public void setCode(int code)
    {
        this.code = code;
    }

    @Override
    public String getMessage()
    {
        return message;
    }

    @Override
    public void setMessage(String message)
    {
        this.message = message;
    }
}
