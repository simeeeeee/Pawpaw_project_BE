package kr.co.pawpaw.api.dto.position;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.pawpaw.mysql.position.Position;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PositionRequest {
    @NotNull
    @Schema(description = "위도", example = "36.5")
    private Double latitude;
    @NotNull
    @Schema(description = "경도", example = "36.4")
    private Double longitude;
    @NotBlank
    @Schema(description = "장소 이름", example = "서울특별시 강동구 고덕동")
    private String name;

    public Position toEntity() {
        return Position.builder()
            .latitude(latitude)
            .longitude(longitude)
            .name(name)
            .build();
    }
}
