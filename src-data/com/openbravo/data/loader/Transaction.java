//    Openbravo POS is a point of sales application designed for touch screens.
//    Copyright (C) 2007-2009 Openbravo, S.L.
//    http://www.openbravo.com/product/pos
//
//    This file is part of Openbravo POS.
//
//    Openbravo POS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    Openbravo POS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with Openbravo POS.  If not, see <http://www.gnu.org/licenses/>.
package com.openbravo.data.loader;

import java.sql.SQLException;
import com.openbravo.basic.BasicException;

/**
 *
 * @author adrianromero Created on 26 de febrero de 2007, 21:50
 *
 */
public abstract class Transaction<T> {

    private Session s;

    /**
     * Creates a new instance of Transaction
     */
    public Transaction(Session s) {
        this.s = s;
    }

    public final T execute() throws BasicException {
        System.out.println("Calling Transaction");
        if (s.isTransaction()) {
           s.setTransaction();
            System.out.println("Inside is transaction");
            return transact();
        } else {
            try {
                try {
                     System.out.println("Outside is transaction");
                    s.begin();
                    System.out.println("Transact Begin");
                    T result = transact();
                    s.commit();
                    System.out.println("Commit Ends");
                    return result;
                } catch (BasicException e) {
                    System.out.println("INSIDE ROLLBACK" );
                    e.printStackTrace();
                    s.rollback();
                    throw e;
                }
            } catch (SQLException eSQL) {
                System.out.println("Inside SQL exception");
                throw new BasicException("Transaction error", eSQL);
            }
        }
    }

    protected abstract T transact() throws BasicException;
}
