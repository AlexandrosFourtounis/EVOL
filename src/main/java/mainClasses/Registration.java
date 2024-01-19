/* csd5031 , csd5020 , csd4845
* HY-360 Registration.java
 */
package mainClasses;


public class Registration {
    int registration_id, customer_id;
    String username, pass, registration_date;

    public int getRegistartion_id() {
        return registration_id;
    }

    public void setRegistartion_id(int registration_id) {
        this.registration_id = registration_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return pass;
    }

    public void setPassword(String password) {
        this.pass = password;
    }

    public String getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }

}
