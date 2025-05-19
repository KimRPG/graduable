package com.software.graduable.roadmap;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class RoadMapDTO {
    private String yearSemester;
    private List<RoadMapSemesterDTO> roadMapSemesterDTO;

    public RoadMapDTO(Map<String, List<RoadMapSemesterDTO>> map, String key) {
        this.yearSemester = key;
        this.roadMapSemesterDTO = map.get(key);
    }
}
