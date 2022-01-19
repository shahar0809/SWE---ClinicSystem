package il.cshaifasweng.OCSFMediatorExample.requests;

import il.cshaifasweng.OCSFMediatorExample.entities.ClinicMember;

public class GetMemberAppointmentsRequest extends Request {
    private ClinicMember member;

    public GetMemberAppointmentsRequest(ClinicMember member) {
        this.member = member;
    }

    public ClinicMember getMember() {
        return member;
    }

    public void setMember(ClinicMember member) {
        this.member = member;
    }

    @Override
    public String getType() {
        return "GetMemberAppointmentsRequest";
    }
}
