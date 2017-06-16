//    Openbravo POS is a point of sales application designed for touch screens.
//    Copyright (C) 2008-2009 Openbravo, S.L.
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

package com.openbravo.pos.sales;

import com.openbravo.pos.ticket.TaxInfo;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author adrianromero
 */
public class TaxesLogicElement {
    
    private TaxInfo tax;
    private List<TaxesLogicElement> taxsons;
    
    public TaxesLogicElement(TaxInfo tax) {
        this.tax = tax;
        this.taxsons = new ArrayList<TaxesLogicElement>();
    }
    
    public TaxInfo getTax() {
        return tax;
    }
    
    public List<TaxesLogicElement> getSons() {
        return taxsons;
    }

    //Sorting taxes based on its calculation type(1.LNA, 2.LNA tax,3.tax)
    public static Comparator<TaxesLogicElement> CustomComparator = new Comparator<TaxesLogicElement>() {

        @Override
        public int compare(TaxesLogicElement l1, TaxesLogicElement l2) {
           return l1.getTax().getBaseAmt().compareTo(l2.getTax().getBaseAmt());
        }
    };
}
