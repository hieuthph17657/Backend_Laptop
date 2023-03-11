package datnnhom12api.request;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StorageDetailRequest {
    private Long storageTypeId;

    private String type;

    private String capacity;
}
