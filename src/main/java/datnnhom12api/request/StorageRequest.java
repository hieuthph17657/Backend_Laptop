package datnnhom12api.request;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StorageRequest {

    private Long storageDetailId;

    private int total;

    private int number;
}
