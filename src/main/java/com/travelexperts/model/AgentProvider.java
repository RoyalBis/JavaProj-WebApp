package com.travelexperts.model;

import com.travelexperts.business.Agent;
import com.travelexperts.business.IEntity;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.String.join;

/**
 * Created by 723403 on 3/9/2016.
 */
public class AgentProvider extends EntityProvider
{
    protected static IProvider provider = new AgentProvider();

    //TABLE
    public static final String table = "Agents";
    //All columns
    public static final String[] allColumns =
    {
        "AgentId",
        "AgtFirstName",
        "AgtMiddleInitial",
        "AgtLastName",
        "AgtBusPhone",
        "AgtEmail",
        "AgtPosition",
        "AgencyId",
        "Active"
    };

    //SQL STATEMENTS
    private static String getAllAgents =
                    "SELECT " + join(", ", allColumns) + " " +
                    "FROM " + table + " ORDER BY " + allColumns[0];
    private static String getAgentById =
                    "SELECT " + join(", ", allColumns) + " " +
                    "FROM " + table + " " +
                    "WHERE " + allColumns[0] + " = ?";
    private static String insertAgent =
                    "INSERT INTO " + table + " " +
                    "(" +
                        allColumns[1] + ", " +
                        allColumns[2] + ", " +
                        allColumns[3] + ", " +
                        allColumns[4] + ", " +
                        allColumns[5] + ", " +
                        allColumns[6] + ", " +
                        allColumns[7] + ", " +
                        allColumns[8] +
                    ") " +
                    "Values(" + repeat("?", allColumns.length - 1) + ")";
    private static String updateAgent =
                    "UPDATE " + table + " " +
                    "SET " +
                        allColumns[1] + " = ?, " +
                        allColumns[2] + " = ?, " +
                        allColumns[3] + " = ?, " +
                        allColumns[4] + " = ?, " +
                        allColumns[5] + " = ?, " +
                        allColumns[6] + " = ?, " +
                        allColumns[7] + " = ?, " +
                        allColumns[8] + " = ? " +
                    "WHERE " + allColumns[0] + " = ? " +
                    "AND "   + allColumns[1] + " = ? " +
                    "AND "   + allColumns[2] + " = ? " +
                    "AND "   + allColumns[3] + " = ? " +
                    "AND "   + allColumns[4] + " = ? " +
                    "AND "   + allColumns[5] + " = ? " +
                    "AND "   + allColumns[6] + " = ? " +
                    "AND "   + allColumns[7] + " = ? " +
                    "AND "   + allColumns[8] + " = ?";

    public static ArrayList GetAll()
    {
        String sql = getAllAgents;
        return provider.FetchAll(sql);
    }

    public static Agent GetById(int id)
    {
        String sql = getAgentById;
        return (Agent) provider.Fetch(sql, id);
    }

    //GET WHERE OVERLOADS///////////////////////////////////////////////////////////////////////////////
    public static ArrayList GetWhere(String col, String val )
    {
        String sql = prepWhere(col);
        return provider.FetchWhere(sql, val);
    }

    public static ArrayList GetWhere(String col, int val )
    {
        String sql = prepWhere(col);
        return provider.FetchWhere(sql, val);
    }

    public static ArrayList GetWhere(String col, boolean val )
    {
        String sql = prepWhere(col);
        return provider.FetchWhere(sql, val);
    }

    public static ArrayList GetWhere(String col, java.sql.Date val )
    {
        String sql = prepWhere(col);
        return provider.FetchWhere(sql, val);
    }

    public static ArrayList GetWhere(String col, BigDecimal val )
    {
        String sql = prepWhere(col);
        return provider.FetchWhere(sql, val);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    private static String prepWhere(String col){
        int i;
        for (i = 0; i < allColumns.length; i++)
        {
            if(col.equals(allColumns[i]))
            {
                break;
            }
        }
        String sql =
                "SELECT " + join(", ", allColumns) + " " +
                        "FROM " + table + " " +
                        "WHERE " + allColumns[i] + "  = ?";
        return sql;
    }

    public static boolean Add(Agent agent)
    {
        String sql = insertAgent;
        return provider.Insert(sql, agent);
    }

    public static boolean Modify(Agent newAgent, Agent oldAgent)
    {
        String sql = updateAgent;
        return provider.Update(sql, newAgent, oldAgent);
    }

    public IEntity Construct(ResultSet rs) throws SQLException
    {
        int i = 1;
        Agent agent = new Agent();
        agent.setAgentId(rs.getInt(i++));
        agent.setAgtFirstName(rs.getString(i++));
        agent.setAgtMiddleInitial(rs.getString(i++));
        agent.setAgtLastName(rs.getString(i++));
        agent.setAgtBusPhone(rs.getString(i++));
        agent.setAgtEmail(rs.getString(i++));
        agent.setAgtPosition(rs.getString(i++));
        agent.setAgencyId(rs.getInt(i++));
        agent.setActive(rs.getBoolean(i++));
        return agent;
    }

    @Override
    public PreparedStatement PrepEntity(PreparedStatement stmt, IEntity entity) throws SQLException
    {
        int i = 1;
        Agent agent = (Agent)entity;
        stmt.setString(i++, agent.getAgtFirstName());
        stmt.setString(i++, agent.getAgtMiddleInitial());
        stmt.setString(i++, agent.getAgtLastName());
        stmt.setString(i++, agent.getAgtBusPhone());
        stmt.setString(i++, agent.getAgtEmail());
        stmt.setString(i++, agent.getAgtPosition());
        stmt.setInt(i++, agent.getAgencyId());
        stmt.setBoolean(i++, agent.isActive());
        return stmt;
    }

    public PreparedStatement PrepareUpdate(PreparedStatement stmt, IEntity newEntity, IEntity oldEntity) throws SQLException
    {
        int i = 1;
        Agent newAgent = (Agent)newEntity;
        Agent oldAgent = (Agent)oldEntity;
        stmt.setString(i++, newAgent.getAgtFirstName());
        stmt.setObject(i++, newAgent.getAgtMiddleInitial());
        stmt.setString(i++, newAgent.getAgtLastName());
        stmt.setString(i++, newAgent.getAgtBusPhone());
        stmt.setString(i++, newAgent.getAgtEmail());
        stmt.setString(i++, newAgent.getAgtPosition());
        stmt.setInt(i++, newAgent.getAgencyId());
        stmt.setBoolean(i++, newAgent.isActive());
        stmt.setInt(i++, oldAgent.getAgentId());
        stmt.setString(i++, oldAgent.getAgtFirstName());
        stmt.setObject(i++, oldAgent.getAgtMiddleInitial());
        stmt.setString(i++, oldAgent.getAgtLastName());
        stmt.setString(i++, oldAgent.getAgtBusPhone());
        stmt.setString(i++, oldAgent.getAgtEmail());
        stmt.setString(i++, oldAgent.getAgtPosition());
        stmt.setInt(i++, oldAgent.getAgencyId());
        stmt.setBoolean(i++, oldAgent.isActive());
        return stmt;
    }
}
