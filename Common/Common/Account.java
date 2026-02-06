package Common;

public class Account {

    private String email;
    private String password;
    private String pid;
    
    public Account() {
    }

    public Account(String email, String password, String pid) {
        this.email = email;
        this.password = password;
        this.pid = pid;
    }

    // Getter & Setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPID() {
    	return pid;
    }
    
    public void setPID(String pid) {
    	this.pid = pid;
    }
}
