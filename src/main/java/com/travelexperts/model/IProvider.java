package com.travelexperts.model;


import com.travelexperts.business.IEntity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 723403 on 3/10/2016.
 */
public interface IProvider
{
    IEntity Construct(ResultSet rs) throws SQLException;
    PreparedStatement PrepEntity(PreparedStatement stmt, IEntity entity) throws SQLException;
    PreparedStatement PrepareUpdate(PreparedStatement stmt, IEntity newEntity, IEntity oldEntity) throws SQLException;
    ArrayList FetchAll(String sql);
    IEntity Fetch(String sql, int id);
    ArrayList FetchWhere(String sql, String val);
    ArrayList FetchWhere(String sql, int val);
    ArrayList FetchWhere(String sql, boolean val);
    ArrayList FetchWhere(String sql, Date val);
    ArrayList FetchWhere(String sql, BigDecimal val);
    boolean Insert(String sql, IEntity entity);
    boolean Update(String sql, IEntity newEntity, IEntity oldEntity);
    boolean Delete(String sql, IEntity entity);
}
