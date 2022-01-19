package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class Report implements Serializable {
    public String name;
    public String result;

    public Report(String name, String result) {
        this.name = name;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
