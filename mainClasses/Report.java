/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainClasses;

/**
 *
 * @author georgia_tsanta
 */
public class Report {
    int report_id, vehicle_id, customer_id;
    String malfunction_description, report_date;
    double repair_cost;
    boolean insurance_paid;

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public double repair_cost() {
        return repair_cost;
    }

    public void setrepair_cost(double repair_cost) {
        this.repair_cost = repair_cost;
    }

    public String getMalfunction_description() {
        return malfunction_description;
    }

    public void setMalfunction_description(String malfunction_description) {
        this.malfunction_description = malfunction_description;
    }

    public String getReport_date() {
        return report_date;
    }

    public void setReport_date(String report_date) {
        this.report_date = report_date;
    }

    public boolean getInsurance_paid() {
        return insurance_paid;
    }

    public void setInsurance_paid(boolean insurance_paid) {
        this.insurance_paid = insurance_paid;
    }
}
