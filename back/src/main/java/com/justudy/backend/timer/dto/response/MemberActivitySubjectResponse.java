package com.justudy.backend.timer.dto.response;

import lombok.Data;

@Data
public class MemberActivitySubjectResponse {

  private String category;
  private Integer time;

  public MemberActivitySubjectResponse(String category, Integer time) {
    this.category = category;
    this.time = time;
  }

  public static MemberActivitySubjectChange changeData(
      MemberActivitySubjectResponse inputResponse) {
    if (inputResponse == null) {
      return null;
    }
    Integer sumTime = inputResponse.getTime();

    String alltime = new StringBuilder(String.format("%03d", sumTime / 1440)).append(":")
        .append(String.format("%02d", (sumTime % 1440) / 24)).append(":")
        .append(String.format("%02d", sumTime % 60)).toString();

    return new MemberActivitySubjectChange(inputResponse.getCategory(),alltime);
  }
}
