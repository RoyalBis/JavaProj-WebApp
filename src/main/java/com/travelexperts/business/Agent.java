package com.travelexperts.business;

/**
 * Created by 723403 on 3/9/2016.
 */
public class Agent implements IEntity
{
    private int AgentId;
    private String AgtFirstName;
    private String AgtMiddleInitial;
    private String AgtLastName;
    private String AgtBusPhone;
    private String AgtEmail;
    private String AgtPosition;
    private int AgencyId;
    private boolean Active;

    //Default Constructor
    public Agent() {}

    //Full Constructor
    public Agent(int agentId, String agtFirstName, String agtMiddleInitial, String agtLastName, String agtBusPhone, String agtEmail, String agtPosition, int agencyId, boolean active)
    {
        AgentId = agentId;
        AgtFirstName = agtFirstName;
        AgtMiddleInitial = agtMiddleInitial;
        AgtLastName = agtLastName;
        AgtBusPhone = agtBusPhone;
        AgtEmail = agtEmail;
        AgtPosition = agtPosition;
        AgencyId = agencyId;
        Active = active;
    }

    public int getAgentId()
    {
        return AgentId;
    }

    public void setAgentId(int agentId)
    {
        AgentId = agentId;
    }

    public String getAgtFirstName()
    {
        return AgtFirstName;
    }

    public void setAgtFirstName(String agtFirstName)
    {
        AgtFirstName = agtFirstName;
    }

    public String getAgtMiddleInitial()
    {
        return AgtMiddleInitial;
    }

    public void setAgtMiddleInitial(String agtMiddleInitial)
    {
        AgtMiddleInitial = agtMiddleInitial;
    }

    public String getAgtLastName()
    {
        return AgtLastName;
    }

    public void setAgtLastName(String agtLastName)
    {
        AgtLastName = agtLastName;
    }

    public String getAgtBusPhone()
    {
        return AgtBusPhone;
    }

    public void setAgtBusPhone(String agtBusPhone)
    {
        AgtBusPhone = agtBusPhone;
    }

    public String getAgtEmail()
    {
        return AgtEmail;
    }

    public void setAgtEmail(String agtEmail)
    {
        AgtEmail = agtEmail;
    }

    public String getAgtPosition()
    {
        return AgtPosition;
    }

    public void setAgtPosition(String agtPosition)
    {
        AgtPosition = agtPosition;
    }

    public int getAgencyId()
    {
        return AgencyId;
    }

    public void setAgencyId(int agencyId)
    {
        AgencyId = agencyId;
    }

    public boolean isActive() { return Active; }

    public void setActive(boolean active) { Active = active; }

    @Override
    public String toString()
    {
        return "Agent{" +
               "AgentId=" + AgentId +
               ", AgtFirstName='" + AgtFirstName + '\'' +
               ", AgtMiddleInitial='" + AgtMiddleInitial + '\'' +
               ", AgtLastName='" + AgtLastName + '\'' +
               ", AgtBusPhone='" + AgtBusPhone + '\'' +
               ", AgtEmail='" + AgtEmail + '\'' +
               ", AgtPosition='" + AgtPosition + '\'' +
               ", AgencyId=" + AgencyId +
               ", Active=" + Active +
               '}';
    }

    @Override
    public int getId()
    {
        return getAgentId();
    }

    @Override
    public void setId(int id)
    {
        setAgentId(id);
    }
}
