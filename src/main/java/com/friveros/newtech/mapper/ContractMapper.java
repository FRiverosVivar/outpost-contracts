package com.friveros.newtech.mapper;

import com.friveros.newtech.Contract;
import org.bson.types.ObjectId;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE)
public interface ContractMapper {
    /**
     * Maps all fields from {@code fight} to a {@link com.friveros.newtech.schema.Contract}
     * @param contract
     * @return
     */
    com.friveros.newtech.schema.Contract toSchema(Contract contract);
    @Mapping(target = "id", ignore = true)
    public abstract void merge(Contract dto, @MappingTarget Contract entity);
    default String toString(ObjectId objectId) {
        return objectId.toString();
    }
}
