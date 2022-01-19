package il.cshaifasweng.OCSFMediatorExample.response;

import il.cshaifasweng.OCSFMediatorExample.entities.Report;

import java.util.List;

public class GetClinicReportsResponse extends Response {
    public List<Report> reports;

    public GetClinicReportsResponse(List<Report> reports, boolean isSuccessful, String error) {
        super(isSuccessful, error);
        this.reports = reports;
    }

    public GetClinicReportsResponse(List<Report> reports, boolean isSuccessful) {
        super(isSuccessful);
        this.reports = reports;
    }

    @Override
    public String getType() {
        return "GetClinicReportsResponse";
    }
}
