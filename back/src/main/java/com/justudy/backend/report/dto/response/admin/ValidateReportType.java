package com.justudy.backend.report.dto.response.admin;

import com.justudy.backend.report.domain.*;

public class ValidateReportType {

    public static String valid(Report report) {
        if (report instanceof MemberReport) {
            return "member";
        }
        if (report instanceof CommunityReport) {
            return "community";
        }
        if (report instanceof CommentReport) {
            return "comment";
        }
        if (report instanceof StudyReport) {
            return "study";
        }
        return null;
    }
}
