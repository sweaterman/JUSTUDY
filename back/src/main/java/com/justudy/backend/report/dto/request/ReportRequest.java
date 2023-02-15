package com.justudy.backend.report.dto.request;

import com.justudy.backend.report.exception.ReportReasonNotFound;
import com.justudy.backend.report.exception.ReportTypeNotFound;
import lombok.Data;

import java.util.Arrays;

@Data
public class ReportRequest {

    private ReportType type;

    private ReasonModel reason;

    public ReportRequest(String type, String reason) {
        this.type = stringToEnum(type);
        this.reason = searchReason(this.type, reason);
    }

    public ReportType stringToEnum(String type) {
        return Arrays.stream(ReportType.class.getEnumConstants())
                .filter(reportType -> reportType.getValue().equalsIgnoreCase(type))
                .findFirst()
                .orElseThrow(ReportTypeNotFound::new);
    }

    public ReasonModel searchReason(ReportType type, String reason) {
        return type.getReasons().stream()
                .filter(e -> e.getValue().equals(reason))
                .findFirst()
                .orElseThrow(ReportReasonNotFound::new);
    }
}
