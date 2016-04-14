//    Chromis POS  - The New Face of Open Source POS
//    Copyright (c) (c) 2015-2016
//    http://www.chromis.co.uk
//
//    This file is part of Chromis POS
//
//     Chromis POS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    Chromis POS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with Chromis POS.  If not, see <http://www.gnu.org/licenses/>.
package uk.chromis.pos.payment;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import uk.chromis.pos.forms.AppConfig;

/**
 *
 * @author adrianromero
 */
public class JPaymentSelectReceipt extends JPaymentSelect {

    /**
     * Creates new form JPaymentSelect
     *
     * @param parent
     * @param modal
     * @param o
     */
    protected JPaymentSelectReceipt(java.awt.Frame parent, boolean modal, ComponentOrientation o) {
        super(parent, modal, o);
    }

    /**
     * Creates new form JPaymentSelect
     *
     * @param parent
     * @param o
     * @param modal
     */
    protected JPaymentSelectReceipt(java.awt.Dialog parent, boolean modal, ComponentOrientation o) {
        super(parent, modal, o);
    }

    /**
     *
     * @param parent
     * @return
     */
    public static JPaymentSelect getDialog(Component parent) {

        Window window = getWindow(parent);

        if (window instanceof Frame) {
            return new JPaymentSelectReceipt((Frame) window, true, parent.getComponentOrientation());
        } else {
            return new JPaymentSelectReceipt((Dialog) window, true, parent.getComponentOrientation());
        }
    }

    /**
     *
     */
    @Override
    protected void addTabs() {

        if( Boolean.parseBoolean(AppConfig.getInstance().getProperty("payment.acceptcash")) ) {
            addTabPayment(new JPaymentSelect.JPaymentCashCreator());
        }
        if( Boolean.parseBoolean(AppConfig.getInstance().getProperty("payment.acceptvoucher")) ) {
            addTabPayment(new JPaymentSelect.JPaymentPaperCreator());
        }
        if( Boolean.parseBoolean(AppConfig.getInstance().getProperty("payment.acceptcard")) ) {
            addTabPayment(new JPaymentSelect.JPaymentMagcardCreator());      
        }
        if( Boolean.parseBoolean(AppConfig.getInstance().getProperty("payment.acceptfree")) ) {
            addTabPayment(new JPaymentSelect.JPaymentFreeCreator());
        }
        if( Boolean.parseBoolean(AppConfig.getInstance().getProperty("payment.acceptbank")) ) {
            addTabPayment(new JPaymentSelect.JPaymentBankCreator());
        }
        if( Boolean.parseBoolean(AppConfig.getInstance().getProperty("payment.acceptcheque")) ) {
            addTabPayment(new JPaymentSelect.JPaymentChequeCreator());
        }
        if( Boolean.parseBoolean(AppConfig.getInstance().getProperty("payment.acceptaccount")) ) {
            addTabPayment(new JPaymentSelect.JPaymentDebtCreator());
        }
        setHeaderVisible(true);
    }

    /**
     *
     * @param isPositive
     * @param isComplete
     */
    @Override
    protected void setStatusPanel(boolean isPositive, boolean isComplete) {

        setAddEnabled(isPositive && !isComplete);
        setOKEnabled(isComplete);
    }

    /**
     *
     * @param total
     * @return
     */
    @Override
    protected PaymentInfo getDefaultPayment(double total) {
        return new PaymentInfoCash_original(total, total);
    }
}
