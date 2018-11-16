package com.codebytes.partnerportal.api.domain;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.javamoney.moneta.Money;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class MoneyUserType implements UserType
{
    @Override
    public int[] sqlTypes() {
        return new int[] {Types.DOUBLE};
    }
    @Override
    public Class<String> returnedClass() {
        return String.class;
    }
    @Override
    public boolean equals(Object x, Object y) throws HibernateException
    {
        return ((x == y) || (x != null && y != null && x.equals(y)));
    }
    @Override
    public int hashCode(Object x) throws HibernateException {
        return x != null ? x.hashCode() : 0;
    }

    @Override
    public Object nullSafeGet(ResultSet pResultSet, String[] pStrings,
                              SharedSessionContractImplementor pSharedSessionContractImplementor, Object pO)
            throws HibernateException, SQLException
    {
        if (pResultSet.wasNull()) {
            return null;
        }

        return Money.of(new BigDecimal(pStrings[0]), "PHP");
    }

    @Override
    public void nullSafeSet(PreparedStatement pPreparedStatement, Object pO, int pI,
                            SharedSessionContractImplementor pSharedSessionContractImplementor)
            throws HibernateException, SQLException
    {
        if (pO == null) {
            pPreparedStatement.setBigDecimal(pI, new BigDecimal(0.00));
        } else {
            Money money = (Money)pO;
            pPreparedStatement.setBigDecimal(pI, money.getNumberStripped());
        }
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value == null ? null : value;
    }
    @Override
    public boolean isMutable() {
        return false;
    }
    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        Object deepCopy = deepCopy(value);
        if (!(deepCopy instanceof Serializable)) {
            return (Serializable) deepCopy;
        }
        return null;
    }
    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return deepCopy(cached);
    }
    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return deepCopy(original);
    }
}
