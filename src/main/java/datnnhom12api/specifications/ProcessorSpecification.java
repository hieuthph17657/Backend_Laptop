package datnnhom12api.specifications;

import datnnhom12api.core.BaseSpecifications;
import datnnhom12api.entity.ProcessorEntity;

public class ProcessorSpecification extends BaseSpecifications<ProcessorEntity> {

    private static ProcessorSpecification INSTANCE;

    public static ProcessorSpecification getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProcessorSpecification();
        }
        return INSTANCE;
    }
}
